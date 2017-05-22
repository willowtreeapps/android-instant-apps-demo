package com.willowtreeapps.androidinstantappsdemo.feature.browse.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.willowtreeapps.androidinstantappsdemo.data.model.Catalog;
import com.willowtreeapps.androidinstantappsdemo.data.model.Item;
import com.willowtreeapps.androidinstantappsdemo.feature.browse.R;
import com.willowtreeapps.androidinstantappsdemo.feature.browse.injection.DaggerProvider;
import com.willowtreeapps.androidinstantappsdemo.util.FirebaseUtil;
import com.willowtreeapps.androidinstantappsdemo.util.NavigationUtil;

import javax.inject.Inject;
import javax.inject.Named;

import static com.willowtreeapps.androidinstantappsdemo.R.dimen.grid_image_width;
import static com.willowtreeapps.androidinstantappsdemo.injection.ApplicationModule.FIREBASE_STORAGE_PICASSO;

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<CatalogRecyclerAdapter.CatalogViewHolder> {
    private static final String TAG = "CatalogRecyclerAdapter";

    private Catalog catalog;

    private Context context;

    @Inject
    @Named(FIREBASE_STORAGE_PICASSO)
    Picasso picasso;

    public CatalogRecyclerAdapter(Context context, Catalog catalog) {
        this.context = context;
        this.catalog = catalog;
        DaggerProvider.getFeatureComponent(context).inject(this);
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalog, viewGroup, false);
        return new CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder catalogViewHolder, int i) {
        catalogViewHolder.bindView(catalog.getItems().get(i));
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return catalog != null && catalog.getItems() != null ? catalog.getItems().size() : 0;
    }

    class CatalogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView catalogThumbnail;
        private TextView catalogTitle;
        private Item item;

        CatalogViewHolder(View itemView) {
            super(itemView);
            catalogThumbnail = (ImageView) itemView.findViewById(R.id.catalogThumbnail);
            catalogTitle = (TextView) itemView.findViewById(R.id.catalogTitle);
            itemView.setOnClickListener(this);
        }

        void bindView(Item item) {
            this.item = item;
            if (catalogThumbnail != null) {
                int width = itemView.getResources().getDimensionPixelSize(grid_image_width);
                picasso.load(FirebaseUtil.storagePathToUri(item.getImage()))
                        .resize(width, 0)
                        .onlyScaleDown()
                        .into(catalogThumbnail);
            }
            if (catalogTitle != null) {
                catalogTitle.setText(item.getName());
            }
        }

        @Override
        public void onClick(View v) {
            NavigationUtil.goToItem(context, item.getId());
        }

    }


}
