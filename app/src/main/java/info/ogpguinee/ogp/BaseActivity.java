package info.ogpguinee.ogp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity  {

    private static final String TAG = "BaseActivity";
   // protected ConnectionCheck mConnectionCheck = MyApp.getInstance().getConnectionCheck();





    //Check for internet and show Dialog info
    /*protected boolean hasInternet() {
        boolean isConnected = mConnectionCheck.isNetworkConnected();
        if (!isConnected) {
            getCustomInfoDialogBox().
                    setMessage(getString(R.string.internet_error))
                    .show(this);
        }
        return isConnected;
    }

    protected boolean hasInternet(DialogCallback.Info info) {
        boolean isConnected = mConnectionCheck.isNetworkConnected();
        if (!isConnected) {
            getCustomInfoDialogBox().
                    setMessage(getString(R.string.internet_error))
                    .setInfo(info)
                    .show(this);
        }
        return isConnected;
    }
*/

    protected void setFragment(Fragment fragment, String tag, boolean addToStack) {
        if (fragment == null)
            return;
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.f_common, fragment, tag);
            if (addToStack)
                fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } catch (IllegalStateException | NullPointerException ex) {
            AppLogger.Logger.error(TAG, ex.getMessage(), ex);
        }
    }

    protected Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() <= 0)
            return null;
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
        return currentFragment;
    }


    protected boolean checkForBundle(Intent intent, String tag) {
        if (intent == null || intent.getExtras() == null) {
            throw new RuntimeException("Please use static method of Class " + tag + " to start corresponding Activity");
        }
        return true;
    }

    protected String getTextFromView(TextView view) {
        return view.getText().toString().trim();
    }

    protected void setResult(Bundle bundle) {
        setResult(Activity.RESULT_OK, new Intent().putExtras(bundle));
    }


}
