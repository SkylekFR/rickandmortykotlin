package com.emilien.rickandmortykotlin.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.ui.MainActivity
import com.emilien.rickandmortykotlin.webservices.AuthManager
import kotlinx.android.synthetic.*

class RegistrationFragment : Fragment() {
    lateinit var registerEmailEditText: EditText
    lateinit var registerPasswordEditText: EditText
    lateinit var registerButton: Button
    val auth = AuthManager.auth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    companion object {

            private const val TAG = "RegistrationFragment"

        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton = view.findViewById(R.id.fragment_registration_registerButton)
        registerPasswordEditText = view.findViewById(R.id.fragment_registration_registerPasswordEditText)
        registerEmailEditText = view.findViewById(R.id.fragment_registration_registerEmailEditText)
    }

    private fun register(email: String, password: String) {
        if(!email.isEmpty() && !password.isEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(RegistrationFragment.TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(context, "You successfully created your account. You're now connected",
                            Toast.LENGTH_SHORT).show()
                       // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(RegistrationFragment.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
        }
    }
}