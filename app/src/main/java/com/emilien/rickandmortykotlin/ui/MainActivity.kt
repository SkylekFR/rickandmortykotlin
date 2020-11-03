package com.emilien.rickandmortykotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emilien.rickandmortykotlin.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : Activity() {
    val RC_SIGN_IN = 1001
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("215644384276-7rg4bb2ha8jre38hsj3s9gmt1m61747b.apps.googleusercontent.com")
        .requestEmail()
        .build()

    lateinit var gotoappBtn: Button
    lateinit var signinBtn: SignInButton
    lateinit var infoTv: TextView
    lateinit var disconnectButton: Button
    lateinit var registerButton: Button
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var registerEmailEditText: EditText
    lateinit var registerPasswordEditText: EditText
    lateinit var connectButton: Button
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        gotoappBtn = findViewById(R.id.activity_main_gotoappBtn)
        signinBtn = findViewById(R.id.activity_main_signinBtn)
        infoTv = findViewById(R.id.activity_main_infoTv)
        disconnectButton = findViewById(R.id.activity_main_disconnectButton)
        registerButton = findViewById(R.id.activity_main_registerButton)
        emailEditText = findViewById(R.id.activity_main_emailEditText)
        passwordEditText = findViewById(R.id.activity_main_passwordEditText)
        registerEmailEditText = findViewById(R.id.activity_main_registerEmailEditText)
        registerPasswordEditText = findViewById(R.id.activity_main_registerPasswordEditText)
        connectButton = findViewById(R.id.activity_main_connectButton)
        gotoappBtn.visibility = View.GONE
        gotoappBtn.text = "Start"
        attachListeners()
    }

    override fun onStart() {
        super.onStart()
        updateUI(auth.currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("GoogleSIGNIN", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("GoogleSIGNIN", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun attachListeners() {
        signinBtn.setOnClickListener(clickListener)
        gotoappBtn.setOnClickListener(clickListener)
        connectButton.setOnClickListener(clickListener)
        disconnectButton.setOnClickListener(clickListener)
        registerButton.setOnClickListener(clickListener)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser == null) {
            /// disconnected
           infoTv.setText("You need to connect to use the app")
            signinBtn.visibility = View.VISIBLE
            gotoappBtn.visibility = View.GONE
            emailEditText.isEnabled = true
            passwordEditText.isEnabled = true
            connectButton.isEnabled = true
            disconnectButton.isEnabled = false
            registerButton.isEnabled = true

        }
        else{
            /// connected
            infoTv.setText("Welcome ${auth.currentUser?.email}")
            signinBtn.visibility = View.GONE
            gotoappBtn.visibility = View.VISIBLE
            emailEditText.isEnabled = false
            passwordEditText.isEnabled = false
            connectButton.isEnabled = false
            disconnectButton.isEnabled = true
            registerButton.isEnabled = false
        }
    }
    val clickListener = View.OnClickListener { view ->

        when (view.getId()) {
            R.id.activity_main_gotoappBtn -> startCharacterListActivity()
            R.id.activity_main_signinBtn -> signIn()
            R.id.activity_main_disconnectButton -> disconnect()
            R.id.activity_main_connectButton -> connect()
            R.id.activity_main_registerButton -> register(registerEmailEditText.text.toString(), registerPasswordEditText.text.toString())
        }
    }

    private fun startCharacterListActivity() {
        val intent = Intent(this, CharacterListActivity::class.java)
        startActivity(intent)
    }

    private fun disconnect() {
            FirebaseAuth.getInstance().signOut()
            updateUI(null);
    }
    private fun connect() {
        if(!emailEditText.text.isEmpty() && !passwordEditText.text.isEmpty()) {
            auth.signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "connectedWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }
        }
    }

    private fun register(email: String, password: String) {
        if(!email.isEmpty() && !password.isEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "You successfully created your account. You're now connected",
                            Toast.LENGTH_SHORT).show()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun signIn() {
        val signInIntent = GoogleSignIn.getClient(this, gso).signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }





    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FirebaseAuth", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("FirebaseAuth", "signInWithCredential:failure", task.exception)
                    // ...

                    updateUI(null)
                }

                // ...
            }
    }






}