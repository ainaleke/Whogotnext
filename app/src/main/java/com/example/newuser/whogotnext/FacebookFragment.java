package  com.example.newuser.whogotnext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;



/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FacebookFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename and change types of parameters
    private LoginButton loginButton;
    CallbackManager callbackManager;
    private OnFragmentInteractionListener mListener;

    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_facebook_button, container, false);
        loginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setOnClickListener(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent chooseActivityIntent = new Intent(getContext(), ChooseActivity.class);
                startActivity(chooseActivityIntent);
            }

            @Override
            public void onCancel() {

            }


            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "Something went wrong! Try again!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }






    @Override
    public void onClick(View v) {
        System.out.println("FACEBOOK BUTTON CLICK");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
