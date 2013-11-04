package hr.foi.air.g2.vjezbe.core;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.SupportMapFragment;

public class MySupportMapFragment extends SupportMapFragment {
	public View mOriginalContentView;
	public TouchableWrapper mTouchView;
	private OnMyTouchListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		mOriginalContentView = super.onCreateView(inflater, parent,
				savedInstanceState);
		mTouchView = new TouchableWrapper(getActivity());
		mTouchView.addView(mOriginalContentView);
		return mTouchView;
	}

	@Override
	public View getView() {
		return mOriginalContentView;
	}

	public void setOnMyTouchListener(OnMyTouchListener listener) {
		this.listener = listener;
	}

	public class TouchableWrapper extends FrameLayout {
		public TouchableWrapper(Context context) {
			super(context);
		}

		@Override
		public boolean dispatchTouchEvent(MotionEvent event) {
			if (listener != null) {
				listener.onMyTouchListener(event);
			}

			return super.dispatchTouchEvent(event);
		}
	}

}
