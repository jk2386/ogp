package info.ogpguinee.ogp;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

// In this case, the fragment displays simple text based on the page

@EFragment(R.layout.slide_menu_fragment)
public class ActualitesFragment extends Fragment {
    public static final String TAG = "ActualitesFragment";

    @ViewById(R.id.web_view)
    WebView mWebView;

    @FragmentArg
    String mUrl;

    @Bean
    CustomProgressDialog mProgressDialog;

    private WebViewClient mClient;

    public static ActualitesFragment newInstance(String url) {
        ActualitesFragment fragment = ActualitesFragment_.builder()
                .mUrl(url)
                .build();
        return fragment;
    }


    @AfterViews
    void setUpView() {
        showHideProgress(true);
        if (18 < Build.VERSION.SDK_INT) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        setUpWebView();
        mWebView.loadUrl(mUrl);
    }

    private void showHideProgress(boolean show) {
        if(mProgressDialog==null)
            return;

        if (show)
            mProgressDialog.showDialog();
        else
            mProgressDialog.dismissDialog();
    }



    private void setUpClientView() {
        mClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                showHideProgress(false);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                showHideProgress(false);
            }
        };


    }


    private void setUpWebView() {
        setUpClientView();
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 30)
                    showHideProgress(false);
                // Your custom code.
            }
        });
        mWebView.setWebViewClient(mClient);
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

}

