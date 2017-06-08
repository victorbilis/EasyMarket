package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Pague Menos");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("GoodBom");
        host.addTab(spec);


        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab3);
        spec.setIndicator("São Vicente");
        host.addTab(spec);

        host.setCurrentTab(0);
        lista = (ListView) findViewById(R.id.listView2);
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (host.getCurrentTab()) {
                    case 0:
                        String valor = "SELECT * FROM paguemenos;";
                        new getProdutos(menu.this, lista, valor).execute("");
                        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                                String data = "SELECT * FROM paguemenos WHERE Nome='" + list.getItem(position).toString() + "'";
                                new getPreco(data, menu.this).execute("");
                                return false;
                            }
                        });
                        break;
                    case 1:
                        String valor2 = "SELECT * FROM goodbom;";
                        new getProdutos(menu.this, lista, valor2).execute("");
                        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                                String data = "SELECT * FROM goodbom WHERE Nome='" + list.getItem(position).toString() + "'";
                                new getPreco(data, menu.this).execute("");
                                return false;
                            }
                        });
                        break;
                    case 2:
                        String valor3 = "SELECT * FROM saovicente;";
                        new getProdutos(menu.this, lista, valor3).execute("");
                        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                                String data = "SELECT * FROM saovicente WHERE Nome='" + list.getItem(position).toString() + "'";
                                new getPreco(data, menu.this).execute("");
                                return false;
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
        String valor = "SELECT * FROM paguemenos;";
        new getProdutos(menu.this, lista, valor).execute("");
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                String data = "SELECT * FROM paguemenos WHERE Nome='" + list.getItem(position).toString() + "'";

                new getPreco(data, menu.this).execute("");
                return false;
            }
        });











        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(menu.this,mercados.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(menu.this,lista.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(menu.this,paguemenos.class);
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(menu.this,goodbom.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(menu.this,saovicente.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
