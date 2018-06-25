package br.com.trmasolutions.pibaruja.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.transition.ChangeBounds
import android.util.Log
import android.view.Menu
import br.com.trmasolutions.pibaruja.BuildConfig
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.model.Event
import br.com.trmasolutions.pibaruja.utils.extension.loadImage
import br.com.trmasolutions.pibaruja.utils.extension.showProgress
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    private var shareActionProvider: ShareActionProvider? = null
    private var event: Event? = null
    private lateinit var contentUri: Uri
    private var storage = FirebaseStorage.getInstance()
    private lateinit var bitmapShare: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {

        setAnimation()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        showProgress(textViewDescription, progressBar, true)

        event = intent?.getParcelableExtra("event")

        showEvent()

        imageBack.setOnClickListener {
            setAnimation()
            finish()
        }

        contentUri = Uri.EMPTY
    }

    private fun setAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000
            window.sharedElementExitTransition = changeBounds
        }
    }

    private fun showEvent() {
        imageViewLogo.isDrawingCacheEnabled = true
        imageViewLogo.loadImage(event?.image, progressImage)
        toolbar_layout.title = event?.name
        textViewName.text = event?.name
        textViewDescription.text = event?.description
        showProgress(textViewDescription, progressBar, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_detail, menu)
        val shareItem = menu.findItem(R.id.menu_share)

        shareActionProvider = MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider

        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE)
        } else {
            setShareIntent()
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun setShareIntent() {

        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE)
        }

        if (event?.image?.isNotEmpty() == true) {
            val httpsReference = storage.getReferenceFromUrl(event?.image as String)

            val oneMegabyte = 1024 * 1024

            httpsReference.getBytes(oneMegabyte.toLong()).addOnSuccessListener {
                bitmapShare = BitmapFactory.decodeByteArray(it, 0, it.size)
                configShareImage()
            }.addOnFailureListener {
                Log.i("TAG", "Error: ${it.message}")
            }
        } else {
            bitmapShare = BitmapFactory.decodeResource(resources, R.drawable.ic_casal)
            configShareImage()
        }
    }

    private fun configShareImage() {
        try {
            val cachePath = File(this.cacheDir, "/images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/image.png") // overwrites this image every time
            bitmapShare.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            stream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        val imagePath = File(this.cacheDir, "images")
        val newFile = File(imagePath, "image.png")
        contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", newFile)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        shareIntent.setDataAndType(contentUri, contentResolver.getType(contentUri))

        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "${event?.name} \n\n${event?.description}")
        shareActionProvider?.setShareIntent(shareIntent)
    }

    override fun onDestroy() {
        val file = File(contentUri.path)
        if (file.exists()) {
            file.delete()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        const val WRITE_EXTERNAL_STORAGE = 2
    }
}
