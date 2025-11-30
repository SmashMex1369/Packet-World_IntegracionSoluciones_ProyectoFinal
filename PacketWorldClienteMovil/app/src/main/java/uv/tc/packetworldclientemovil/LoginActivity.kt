package uv.tc.packetworldclientemovil

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import uv.tc.packetworldclientemovil.databinding.ActivityLoginBinding
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.root.ajustarAInsets()
        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)
        val view = binding.root
        setContentView(view)

        binding.btnIngresar.setOnClickListener {
            val intent= Intent(this, EnviosActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}

