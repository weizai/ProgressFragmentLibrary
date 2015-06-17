# ProgressFragmentLibrary

it is simple to show empty or progressing when using fragment, all you should do is to extent ProgressFragment


public class DemoFragment extends ProgressFragment {

  	@Override
  	public void onEmptyClick() {
  		
  	}
  
  	@Override
  	public int setContentViewRes() {
  		return R.layout.test;   // show your own contentView
  	}
  	
}
