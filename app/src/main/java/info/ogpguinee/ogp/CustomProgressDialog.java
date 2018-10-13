package info.ogpguinee.ogp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StringRes;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;


@EBean
public class CustomProgressDialog {

    private ProgressDialog mProgressDialog;
    private Context mContext;
    private String mMessage;

    public CustomProgressDialog(Context context) {
        mContext = context;
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCancelable(false);
        setMessage(mContext.getString(R.string.please_wait));
    }


    private String getMessage() {
        return mMessage;
    }

    public CustomProgressDialog setMessage(String message) {
        mMessage = message;
        return this;
    }

    public CustomProgressDialog setMessage(@StringRes int id) {
        mMessage = mContext.getString(id);
        return this;
    }

    public void showDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.custom_progress_dialog);
        ImageView image = (ImageView) mProgressDialog.findViewById(R.id.imageView_loader);

        TextView message = mProgressDialog.findViewById(R.id.txt_progress_message);
        message.setText(getMessage());

        Animation rotation = AnimationUtils.loadAnimation(mContext, R.anim.image_loader);
        rotation.setFillAfter(true);
        image.startAnimation(rotation);
        mProgressDialog.getWindow().setLayout(((Activity) mContext).getWindow().getWindowManager().getDefaultDisplay().getWidth(), ((Activity) mContext).getWindow().getWindowManager().getDefaultDisplay().getHeight());
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void dismissDialog() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    public boolean isShowing() {
        if (mProgressDialog == null)
            return false;

        return mProgressDialog.isShowing();
    }
}
