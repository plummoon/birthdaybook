package lexiong.me.birthdaybook.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lexiong.me.birthdaybook.R;

/**
 * Example about replacing fragments inside a ViewPager. I'm using
 * android-support-v7 to maximize the compatibility.
 *
 * @author Dani Lao (@dani_lao)
 */
public class RootFragment extends Fragment {

    private static final String TAG = RootFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_fragment, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        HomeFragment homeFragment = HomeFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("homeTitle", getString(R.string.birthdaytoday));
        homeFragment.setArguments(bundle);
        transaction.replace(R.id.root_frame, homeFragment,HomeFragment.TAG);

        transaction.commit();

        return view;
    }

}
