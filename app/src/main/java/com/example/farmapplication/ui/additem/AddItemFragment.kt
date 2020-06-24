package com.example.farmapplication.ui.additem

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.calculator.scientific.calculatrice.extension.getFileName
import com.calculator.scientific.calculatrice.extension.getImageUri
import com.example.farmapplication.R
import com.example.farmapplication.data.remote.RetrofitBuilder
import com.example.farmapplication.data.repository.media.MediaRepository
import com.example.farmapplication.extension.snackbar
import com.example.farmapplication.helper.UploadRequestBody
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_add_item.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class AddItemFragment : Fragment(), UploadRequestBody.UploadCallback {

    private val GALLERY = 1
    private val CAMERA = 2

    private var selectedImageUri: Uri? = null

    companion object {
        private val IMAGE_DIRECTORY = "/nongsan"
    }

    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isToolbarShown = false

        img_take_picture.setOnClickListener {
            showPictureDialog()
        }

        btn_upload.setOnClickListener {
            if (ed_name_product.text!!.isNotEmpty() && ed_description.text!!.isNotEmpty()) {
                uploadImage()
            } else {
                layout_root.snackbar("Điền đầy đủ thông tin vào các trường")
            }
        }

    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Chọn phương thức")
        val pictureDialogItems = arrayOf("Chọn hình ảnh từ album", "Chụp từ máy ảnh")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> chooseImageFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun chooseImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) { /* ... */
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {

                }
            }).check()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY) {
            if (data != null) {
                selectedImageUri = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        selectedImageUri
                    )
                    saveImage(bitmap)
                    Toast.makeText(requireContext(), "Image Show!", Toast.LENGTH_SHORT).show()
                    detail_image.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            selectedImageUri = thumbnail.getImageUri(requireContext())
            detail_image.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(requireContext(), "Photo Show!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY
        )
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }
        try {
            val f = File(
                wallpaperDirectory, ((Calendar.getInstance()
                    .timeInMillis).toString() + ".png")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                requireContext(),
                arrayOf(f.path),
                arrayOf("image/png"),
                null
            )
            fo.close()

            return f.absolutePath
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return ""
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun uploadImage() {
        if (selectedImageUri == null) {
            layout_root.snackbar("Select an Image First")
            return
        }

        val sdf = SimpleDateFormat("hh:mm:ss dd/MM/yyyy",Locale.getDefault())
        val currentDate = sdf.format(Date())

        val parcelFileDescriptor =
            requireContext().contentResolver.openFileDescriptor(selectedImageUri!!, "r", null)
                ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(
            requireContext().cacheDir,
            requireContext().contentResolver.getFileName(selectedImageUri!!)
        )
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        progress_bar.progress = 0
        val body = UploadRequestBody(file, "file", this)


        RetrofitBuilder.apiService.uploadFile(
            MultipartBody.Part.createFormData(
                "file",
                file.name,
                body
            ),
            RequestBody.create(
                MediaType.parse("multipart/form-data"),
                ed_name_product.text.toString()
            ),
            RequestBody.create(MediaType.parse("multipart/form-data"), currentDate),
            RequestBody.create(
                MediaType.parse("multipart/form-data"),
                ed_description.text.toString()
            )
        ).enqueue(object : Callback<MediaRepository> {
            override fun onFailure(call: Call<MediaRepository>, t: Throwable) {
                Log.d("AddItemFragment", "onFailure: $t")
                layout_root.snackbar(t.message!!)
                progress_bar.progress = 0
                btn_upload.text = "Tải lên thất bại"
                btn_upload.isEnabled = false

            }

            override fun onResponse(
                call: Call<MediaRepository>,
                response: Response<MediaRepository>
            ) {
                response.body()?.let {
                    Log.d("AddItemFragment", "onResponse: $it")
                    layout_root.snackbar(it.date)
                    progress_bar.progress = 100
                    btn_upload.text = "Tải lên thành công"
                    btn_upload.isEnabled = false
                }
            }
        })

    }

    override fun onProgressUpdate(percentage: Int) {
        progress_bar.progress = percentage
    }


}