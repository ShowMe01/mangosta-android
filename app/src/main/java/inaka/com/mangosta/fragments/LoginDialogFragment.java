package inaka.com.mangosta.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;

import butterknife.BindView;
import butterknife.ButterKnife;
import inaka.com.mangosta.R;
import inaka.com.mangosta.activities.SplashActivity;
import inaka.com.mangosta.xmpp.XMPPSession;

public class LoginDialogFragment extends DialogFragment {

    public static LoginDialogFragment newInstance() {
        return new LoginDialogFragment();
    }

    @BindView(R.id.loginUserNameEditText)
    EditText loginUserNameEditText;

    @BindView(R.id.loginJidCompletionEditText)
    EditText loginJidCompletionEditText;

    @BindView(R.id.loginPasswordEditText)
    EditText loginPasswordEditText;

    @BindView(R.id.loginServerEditText)
    EditText loginServerEditText;

    @BindView(R.id.loginButton)
    Button loginButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_dialog, container, false);
        ButterKnife.bind(this, view);

        toolbar.setTitle(getString(R.string.title_login));

        String userName = "a1";
        String password = "123456";

        loginUserNameEditText.setText(userName);
        loginUserNameEditText.setSelection(userName.length());
        loginJidCompletionEditText.setText("@" + XMPPSession.SERVICE_NAME);

        loginPasswordEditText.setText(password);
        loginServerEditText.setText(XMPPSession.SERVER_NAME);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAndStart(loginUserNameEditText.getText().toString(), loginPasswordEditText.getText().toString());
            }
        });

        return view;
    }

    private void loginAndStart(final String userName, final String password) {
        final ProgressDialog progress = ProgressDialog.show(getActivity(), getString(R.string.loading), null, true);

        Tasks.executeInBackground(getActivity(), new BackgroundWork<Object>() {
            @Override
            public Object doInBackground() throws Exception {
                XMPPSession.startService(getActivity());
                ((SplashActivity) getActivity()).getService().login(userName, password);
                Thread.sleep(XMPPSession.REPLY_TIMEOUT);
                return null;
            }
        }, new Completion<Object>() {
            @Override
            public void onSuccess(Context context, Object result) {
                progress.dismiss();
                ((SplashActivity) getActivity()).startApplication();
            }

            @Override
            public void onError(Context context, Exception e) {
                progress.dismiss();
                XMPPSession.getInstance().getXMPPConnection().disconnect();
                XMPPSession.clearInstance();
                Toast.makeText(context, getString(R.string.error_login), Toast.LENGTH_SHORT).show();
                Log.e("CMB", "onError: " + e);
            }
        });
    }

}
