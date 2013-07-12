/**
 * 
 */
package com.test.simplereader;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.*;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.AbsListView.*;

/**
 * @author Vitor
 *
 */
public class MainActivity extends ListActivity implements ActionBar.OnNavigationListener
{
	public static final String SELECTED_ARTICLE = "selectedArticle";
	
	private final int ARTICLE_QUANTITY = 20;
	
	private ArrayAdapter<String> mListAdapter;
	private SpinnerAdapter mSpinnerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		//creating spinner
		ArrayList<String> dropDownList = new ArrayList<String>();
		dropDownList.add("All articles");
		dropDownList.add("Favorite articles");
		dropDownList.add("Unread articles");
		mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dropDownList);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
		
		//creating list
		ArrayList<String> articles = new ArrayList<String>();
		for(int i = 1; i <= ARTICLE_QUANTITY; i++)
		{
			articles.add("Article " + i);
		}
		
		mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, articles);
		
		setListAdapter(mListAdapter);
		
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		getListView().setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			@Override
			public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onDestroyActionMode(ActionMode arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
				// TODO Auto-generated method stub
				MenuInflater inflater = new MenuInflater(getBaseContext());
				inflater.inflate(R.menu.main_screen_context_menu, arg0.getMenu());
				return true;
			}
			
			@Override
			public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
				arg0.finish();
				return false;
			}
			
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position,
					long id, boolean checked) {
				// TODO Auto-generated method stub
				
				mode.setTitle(getListView().getCheckedItemCount() + " selected");
			}
		});
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Intent intent = new Intent(getBaseContext(), DetailActivity.class);
				intent.putExtra(MainActivity.SELECTED_ARTICLE, arg2 + 1);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_screen_menu, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Toast.makeText(this.getApplicationContext(), "Selected", Toast.LENGTH_SHORT).show();
		return true;
	}

}
