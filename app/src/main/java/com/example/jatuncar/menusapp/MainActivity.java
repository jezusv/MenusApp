package com.example.jatuncar.menusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cardView = findViewById(R.id.cardview);
        registerForContextMenu(cardView);

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode !=null){
                return false;
                }
                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
                v.setSelected(true);
                return true;
            }
        });
    }

    private ActionMode mActionMode = null;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action,menu);
            return true;
        }
        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_save:
                    Toast.makeText(MainActivity.this, "Guardando la nota...", Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.action_share:
                    Toast.makeText(MainActivity.this, "Compartiendo la nota...", Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.action_delete:
                    Toast.makeText(MainActivity.this, "Eliminando la nota...", Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                Toast.makeText(this, "Regresando al inicio...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_takepicture:
                Toast.makeText(this, "Tomando una foto...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_day_view:
                item.setChecked(true);
                Toast.makeText(this, "Vista diaria activada...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_week_view:
                item.setChecked(true);
                Toast.makeText(this, "Vista semanal activada...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_month_view:
                item.setChecked(true);
                Toast.makeText(this, "Vista mensual activada...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_toggle:
                if (item.isChecked()) {
                    item.setChecked(false);
                    Toast.makeText(this, "Modo offline desactivado...", Toast.LENGTH_SHORT).show();
                } else {
                    item.setChecked(true);
                    Toast.makeText(this, "Modo offline activado...", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_about:
                Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.popup,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_favority:
                Toast.makeText(MainActivity.this, "La nota ha sido guardada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_share:
                Toast.makeText(MainActivity.this, "La nota ha sido compartida", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showPopup (View view){
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_favority:
                        Toast.makeText(MainActivity.this, "La nota ha sido guardada", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_share:
                        Toast.makeText(MainActivity.this, "La nota ha sido compartida", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.dash_board:
                        startActivity(new Intent(MainActivity.this,DashboardActivity.class));
                        return true;
                    case R.id.multi_choice:
                        startActivity(new Intent(MainActivity.this,MultiChoiceActivity.class));
                        return true;
                    case R.id.home_activity:
                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            return true;
                    default:
                            return false;
                }
            }
        });
        popupMenu.show();
    }



}
