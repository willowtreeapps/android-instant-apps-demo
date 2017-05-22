package com.willowtreeapps.androidinstantappsdemo.feature.itemdetail.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.willowtreeapps.androidinstantappsdemo.data.model.Item;
import com.willowtreeapps.androidinstantappsdemo.feature.itemdetail.R;
import com.willowtreeapps.androidinstantappsdemo.feature.itemdetail.injection.DaggerProvider;
import com.willowtreeapps.androidinstantappsdemo.ui.base.InstantAppsLifecycleFragment;
import com.willowtreeapps.androidinstantappsdemo.util.FirebaseUtil;

import javax.inject.Inject;
import javax.inject.Named;

import static com.willowtreeapps.androidinstantappsdemo.R.string.price_n;
import static com.willowtreeapps.androidinstantappsdemo.injection.ApplicationModule.FIREBASE_STORAGE_PICASSO;
import static com.willowtreeapps.androidinstantappsdemo.util.Constants.MONEY_FORMAT;

/**
 * A placeholder fragment containing a simple view.
 */
public class ItemDetailActivityFragment extends InstantAppsLifecycleFragment {

    private static final String TAG = ItemDetailActivityFragment.class.getSimpleName();

    public static final String KEY_ITEM_ID = "KEY_ITEM_ID";

    @Inject @Named(FIREBASE_STORAGE_PICASSO)
    Picasso picasso;

    private ImageView bannerImage;
    private TextView name;
    private TextView description;
    private TextView price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bannerImage = (ImageView) view.findViewById(R.id.banner_image);
        name = (TextView) view.findViewById(R.id.name);
        description = (TextView) view.findViewById(R.id.description);
        price = (TextView) view.findViewById(R.id.price);

        Log.i(TAG, getArguments().toString());

        DaggerProvider.getFeatureComponent(getContext()).inject(this);
        ItemDetailViewModel viewModel = ViewModelProviders.of(getActivity()).get(ItemDetailViewModel.class);
        viewModel.setItemId(getArguments().getLong(KEY_ITEM_ID));
        viewModel.getItem().observe(this, this::updateForItem);
    }

    private void updateForItem(Item item) {
        Log.i(TAG, item.toString());
        picasso.load(FirebaseUtil.storagePathToUri(item.getImage())).into(bannerImage);
        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText(getString(price_n, MONEY_FORMAT.format(item.getPrice())));
    }

}
