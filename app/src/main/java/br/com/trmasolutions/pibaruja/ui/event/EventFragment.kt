package br.com.trmasolutions.pibaruja.ui.event

import android.Manifest.permission.*
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.model.Event
import br.com.trmasolutions.pibaruja.utils.extension.showProgress
import br.com.trmasolutions.pibaruja.utils.extension.validateEmpty
import br.com.trmasolutions.pibaruja.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.event_fragment.*


class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
        const val WRITE_EXTERNAL_STORAGE_PERMISSION = 2
        const val CAPTURE_IMAGE_CAMERA_REQUEST_CODE = 3
        const val CAPTURE_IMAGE_GALERY_REQUEST_CODE = 4
    }

    private lateinit var viewModel: EventViewModel
    private var bitmap: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        verifyPermissions()
        setObservables()
        imageListener()
        btnSaveEvent.setOnClickListener {

            if (!validateViews()){
                return@setOnClickListener
            }

            val event = Event()
            event.name = edtName.text.toString()
            event.description = edtDescription.text.toString()
            event.date = edtDate.text.toString()
            event.local = edtLocal.text.toString()
            event.sponsor = edtSponsor.text.toString()
            event.contact = edtContact.text.toString()
            event.video = edtVideo.text.toString()

            viewModel.uploadImageToStorage(bitmap, event)
        }
    }

    private fun imageListener() {
        imgEvent.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context as Context)
            var alertDialog: AlertDialog? = null
            val dialogView = layoutInflater.inflate(R.layout.dialog_camera, null)
            val imgGalery: ImageView = dialogView.findViewById(R.id.imgGalery)
            val imgCamera: ImageView = dialogView.findViewById(R.id.imgCamera)

            imgGalery.setOnClickListener {
                val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(i, CAPTURE_IMAGE_GALERY_REQUEST_CODE)
                alertDialog?.dismiss()
            }

            imgCamera.setOnClickListener {
                verifyPermissions()
                startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAPTURE_IMAGE_CAMERA_REQUEST_CODE)
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
    }

    private fun verifyPermissions(): Boolean {
        val permissionCheck = checkSelfPermission(context as Context, WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA), WRITE_EXTERNAL_STORAGE_PERMISSION)
            return false
        }
        return true
    }

    private fun validateViews() : Boolean{
        if (edtName?.validateEmpty("Nome do evento") == true) {
            return false
        }
        if (edtDescription?.validateEmpty("Descrição do evento") == true) {
            return false
        }
        if (edtDate?.validateEmpty("Data do evento") == true) {
            return false
        }
        if (edtLocal?.validateEmpty("Local do evento") == true) {
            return false
        }
        if (edtSponsor?.validateEmpty("Responsável do evento") == true) {
            return false
        }
        if (edtContact?.validateEmpty("Contato do responsável pelo evento") == true) {
            return false
        }

        return true
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
                    bitmap = BitmapFactory.decodeFile(picturePath)
                    imgEvent.setImageBitmap(bitmap)
                }

                CAPTURE_IMAGE_CAMERA_REQUEST_CODE -> {
                    bitmap = data.extras.get("data") as Bitmap
                    setImageEventBitmap(bitmap)
                    imgEvent.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun setImageEventBitmap(bitmap: Bitmap?) {
        imgEvent.scaleType = ImageView.ScaleType.FIT_XY
        imgEvent.setImageBitmap(bitmap)
    }

    private fun setObservables() {
        viewModel.getEventResponse().observe(this, Observer {
            Log.i("TAG", "error: ${it?.message}")
            resetFields()
        })

        viewModel.getLoadingStatus().observe(this, Observer {
            activity?.showProgress(cardContent, progressBar, it == true)
        })
    }

    private fun resetFields() {
        edtName.setText("")
        edtDescription.setText("")
        edtDate.setText("")
        edtLocal.setText("")
        edtSponsor.setText("")
        edtContact.setText("")
        edtVideo.setText("")
        imgEvent.scaleType = ImageView.ScaleType.CENTER
        imgEvent.setImageResource(R.drawable.ic_photo_library_black_72dp)
    }
}
