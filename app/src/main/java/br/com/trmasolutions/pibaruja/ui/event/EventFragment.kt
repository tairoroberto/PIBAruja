package br.com.trmasolutions.pibaruja.ui.event

import android.Manifest.permission.*
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.model.Event
import br.com.trmasolutions.pibaruja.utils.extension.showValidationAlert
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.event_fragment.*
import java.io.*
import com.google.firebase.storage.StorageReference
import android.R.attr.bitmap
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import java.lang.Exception


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
        const val WRITE_EXTERNAL_STORAGE_PERMISSION = 2
        const val CAPTURE_IMAGE_CAMERA_REQUEST_CODE = 3
        const val CAPTURE_IMAGE_GALERY_REQUEST_CODE = 4
    }

    private lateinit var viewModel: EventViewModel
    var storage = FirebaseStorage.getInstance()
    private var pathImage: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        verifyPermissions()

        imgEvent.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context as Context)
            var alertDialog: AlertDialog? = null
            val dialogView = layoutInflater.inflate(R.layout.dialog_camera, null)
            val imgGalery: ImageView = dialogView.findViewById(R.id.imgGalery)
            val imgCamera: ImageView = dialogView.findViewById(R.id.imgCamera)

            imgGalery.setOnClickListener {
                val i = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(i, CAPTURE_IMAGE_GALERY_REQUEST_CODE)
                alertDialog?.dismiss()
            }

            imgCamera.setOnClickListener {
                verifyPermissions()
                startActivityForResult(Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE), CAPTURE_IMAGE_CAMERA_REQUEST_CODE)
                alertDialog?.dismiss()
            }


            dialogBuilder.setView(dialogView)
            alertDialog = dialogBuilder.create()
            alertDialog.setTitle("Carregar imagem")
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "CANCELAR") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.show()
        }

        btnSaveEvent.setOnClickListener {
            validateViews()

            val event = Event()
            event.name = edtName.text.toString()
            event.description = edtDescription.text.toString()
            event.date = edtDate.text.toString()
            event.local = edtLocal.text.toString()
            event.sponsor = edtSponsor.text.toString()
            event.contact = edtContact.text.toString()
            event.video = edtVideo.text.toString()
        }
    }

    private fun verifyPermissions(): Boolean {
        val permissionCheck = checkSelfPermission(context as Context, WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA), WRITE_EXTERNAL_STORAGE_PERMISSION)
            return false
        }
        return true
    }

    private fun validateViews() {
        if (edtName?.text?.isEmpty() == true) {
            activity?.showValidationAlert("Nome do evento", edtName)
            return
        }
        if (edtDescription?.text?.isEmpty() == true) {
            activity?.showValidationAlert("Descrição do evento", edtDescription)
            return
        }
        if (edtDate?.text?.isEmpty() == true) {
            activity?.showValidationAlert("Data do evento", edtDate)
            return
        }
        if (edtLocal?.text?.isEmpty() == true) {
            activity?.showValidationAlert("Local do evento", edtLocal)
            return
        }
        if (edtSponsor?.text?.isEmpty() == true) {
            activity?.showValidationAlert("Responsável do evento", edtSponsor)
            return
        }
        if (edtContact?.text?.isEmpty() == true) {
            activity?.showValidationAlert("Contato do responsável pelo evento", edtContact)
            return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && null != data) {

            when (requestCode) {
                CAPTURE_IMAGE_GALERY_REQUEST_CODE -> {
                    val selectedImage = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor = activity?.contentResolver?.query(selectedImage, filePathColumn, null, null, null)
                    cursor?.moveToFirst()

                    val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
                    val picturePath = columnIndex?.let { cursor.getString(it) }
                    cursor?.close()
                    val bitmap = BitmapFactory.decodeFile(picturePath)
                    imgEvent.setImageBitmap(bitmap)

                    uploadBitmap(bitmap)
                }

                CAPTURE_IMAGE_CAMERA_REQUEST_CODE -> {
                    val bitmap = data.extras.get("data") as Bitmap
                    imgEvent.setImageBitmap(bitmap)
                    uploadBitmap(bitmap)
                }
            }
        }
    }

    private fun uploadBitmap(bitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        val bitmapData = baos.toByteArray()

        val imagesRef = storage.reference.child("images/event-${System.currentTimeMillis()}.jpg")
        val uploadTask = imagesRef.putBytes(bitmapData)

        uploadTask
                .addOnFailureListener { exception -> Log.i("TAG", "error: ${exception.message}") }
                .addOnSuccessListener { taskSnapshot -> pathImage = taskSnapshot.metadata?.path }
    }
}
