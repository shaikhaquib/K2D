package com.kirana2door.kiranatodoor.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.ViewPagerAdapter;
import com.kirana2door.kiranatodoor.adapters.ProdDetails;
import com.kirana2door.kiranatodoor.adapters.ProductDetailsItem;
import com.kirana2door.kiranatodoor.adapters.ProductImagesItem;
import com.kirana2door.kiranatodoor.api.AppController;
import com.kirana2door.kiranatodoor.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetail extends AppCompatActivity {

    TextView title , desription,description2 , wtut,price ,quantity ;
    ImageView minus ,plus;
    String minimum_quantity,amount ,unit ,s ;
    String[] imagearray ;
    Button addtocart;
    int count = 0,wt ;
    int minteger = 1;
    ViewPager pager;
    ViewDialog progressDialog;
    List<ProductDetailsItem> proddet = new ArrayList<>();
    List<ProductImagesItem> prodimg = new ArrayList<>();

    /*String[] prd_img = {
            "https://www.bigbasket.com/media/uploads/p/s/40112512_2-bb-royal-medjool-dates.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40072455_5-bb-royal-organic-kabuli-chanachanna.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40112523_2-bb-royal-dried-fruit-blueberries.jpg"
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog=new ViewDialog(ProductDetail.this);
         pager =   findViewById(R.id.photos_viewpager);
        quantity = findViewById(R.id.qty);
        minus =findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        title =findViewById(R.id.pname);
        addtocart =findViewById(R.id.addtocart);
        desription = findViewById(R.id.compname);
        description2=findViewById(R.id.descpn);
        wtut=findViewById(R.id.wtut);
        quantity.setText(String.valueOf(minteger));
        price=findViewById(R.id.pprice);


        getData();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minteger<99) {
                    minteger = minteger + 1;
                    quantity.setText(String.valueOf(minteger));
                    int amt = Integer.parseInt(amount);
                    int cal2 = amt * minteger;
                    int cal3 = wt * minteger;
                    s = String.valueOf(cal2);
                    // calculatedprice.setText(s);
                    price.setText("Price:  ₹ "+s);
                    wtut.setText(cal3 +" "+ unit);
                    //pricesenttobuynow = s.trim();
                }

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minteger>1) {
                    minteger = minteger - 1;
                    quantity.setText(String.valueOf(minteger));
                    int amt = Integer.parseInt(amount);
                    int cal2 = amt * minteger;
                    int cal3 = wt * minteger;
                    s = String.valueOf(cal2);
                    // calculatedprice.setText(s);
                    price.setText("Price:  ₹ "+s);
                    wtut.setText(cal3 +" "+ unit);
                    //pricesenttobuynow = s.trim();
                }
                else {
                    quantity.setText("1");
                }

            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (!login){  AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(Product.this);
                    } else {
                        builder = new AlertDialog.Builder(Product.this);
                    }
                    builder.setTitle("Sorry ! please login first")
                            .setMessage("This feature not available for guest user")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    //SessionManager
                                    //context.startActivity(new Intent(context,LoginActivity.class));
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //  startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                }
                            })
                            .show();}else {*/

                    Home outerObject = new Home();
                    Home.AddToCart innerObject = outerObject.new AddToCart();
                    //MainActivity.new AddToCart().execute(current.product_id,current.product_weight,"qwerty@gmail.com");

                /*if(myHolder.quantity.getText().toString().trim() == "1"){
                    innerObject.execute(current.product_id,"1",email);
                }else{*/
                innerObject.execute(getIntent().getStringExtra("product_id"),quantity.getText().toString().trim(),getIntent().getStringExtra("shop_id"),Global.customer_id);
                    //innerObject.execute(getIntent().getStringExtra("product_id"),quantity.getText().toString().trim(), Global.email);
                    //}
                    Snackbar snackbar = Snackbar.make(v, "Product Successfully Added To Cart !", Snackbar.LENGTH_LONG);
                    snackbar.show();
            }
        });
    }

    class VPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;int type ;

        public VPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return prodimg.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = null;


                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.custom_layout, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                //imageView.setImageResource(images[position]);


                Glide.with(context).load(prodimg.get(position).getProductImg()).into(imageView);


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

                ViewPager vp = (ViewPager) container;
                vp.addView(view, 0);

            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }
    }

    private void getData() {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"productdetail", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Gson gson = new Gson();
                ProdDetails res = gson.fromJson(response, ProdDetails.class);
                proddet = res.getProductDetails();
                ProductDetailsItem pdi = proddet.get(0);
                title.setText(pdi.getProductName());
                desription.setText(pdi.getProductDiscription1());
                description2.setText(pdi.getProductDiscription2());
                quantity.setText(pdi.getMinimumQuantity());
                wtut.setText(pdi.getProductWeight()+" "+pdi.getUnits());
                price.setText("Price:  ₹ "+pdi.getProductPrice());
                amount = pdi.getProductPrice();
                unit = pdi.getUnits();
                wt = Integer.parseInt(pdi.getProductWeight());
                prodimg = res.getProductImages();

                VPagerAdapter adapter = new VPagerAdapter(getApplicationContext());
                pager.setAdapter(adapter);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
                tabLayout.setupWithViewPager(pager, true);
                for(int i=0; i < tabLayout.getTabCount(); i++) {
                    View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
                    p.setMargins(0, 0, 15, 0);
                    tab.requestLayout();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> param = new HashMap<String,String>();
                param.put("product_id", getIntent().getStringExtra("product_id"));
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        } return true;
    }
}
