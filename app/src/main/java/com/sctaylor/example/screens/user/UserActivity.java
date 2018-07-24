package com.sctaylor.example.screens.user;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sctaylor.example.R;
import com.sctaylor.example.application.ExampleApplication;
import com.sctaylor.example.application.network.ExampleService;
import com.sctaylor.example.models.Email;
import com.sctaylor.example.screens.user.core.UserContract;
import com.sctaylor.example.screens.user.core.UserEmailAdapter;
import com.sctaylor.example.screens.user.core.UserEmailItemListener;
import com.sctaylor.example.screens.user.core.UserPresenter;
import com.sctaylor.example.screens.user.dagger.components.DaggerUserActivityComponent;
import com.sctaylor.example.screens.user.dagger.components.UserActivityComponent;
import com.sctaylor.example.screens.user.dagger.modules.UserActivityModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class UserActivity extends AppCompatActivity implements UserContract.UserView, UserEmailItemListener {

    @BindView(R.id.textViewFirstNameValue)
    TextView firstNameValue;
    @BindView(R.id.textViewLastNameValue)
    TextView lastNameValue;
    @BindView(R.id.textViewEmailValue)
    TextView emailValue;
    @BindView(R.id.textViewGenderValue)
    TextView genderValue;
    @BindView(R.id.textViewIPValue)
    TextView ipValue;

    @BindView(R.id.imageViewImageValue)
    ImageView imageValue;

    @BindView(R.id.buttonLoadUser)
    Button buttonLoadUser;

    @BindView(R.id.recyclerViewEmails)
    RecyclerView recyclerEmail;

    @Inject
    ExampleService exampleService;

    @Inject
    Picasso picasso;

    @Inject
    UserPresenter presenter;

    @Inject
    UserEmailAdapter emailAdapter;

    private KProgressHUD hudLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        UserActivityComponent component = DaggerUserActivityComponent.builder()
                .userActivityModule(new UserActivityModule(this))
                .exampleApplicationComponent(ExampleApplication.get(this).getComponent())
                .build();

        component.injectUserActivity(this);

        ButterKnife.bind(this);

        presenter.start();

        buttonLoadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.tag("User").i("Loading user...");
                presenter.loadUser();
            }
        });

        hudLoader = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Loading user...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        recyclerEmail.setAdapter(emailAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setAutoMeasureEnabled(false);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerEmail.setLayoutManager(llm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerEmail.getContext(), llm.getOrientation());
        recyclerEmail.addItemDecoration(dividerItemDecoration);

        emailAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateEmailList() {
        emailAdapter.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(int position) {
        Email email = presenter.getEmail(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(email.getSender());

        builder.setMessage(email.getContent());

        builder.show();
    }

    @Override
    public void setFirstName(String firstName) {
        firstNameValue.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        lastNameValue.setText(lastName);
    }

    @Override
    public void setEmail(String email) {
        emailValue.setText(email);
    }

    @Override
    public void setGender(String gender) {
        genderValue.setText(gender);
    }

    @Override
    public void setIPAddress(String ip) {
        ipValue.setText(ip);
    }

    @Override
    public void setImage(String imageUrl) {
        picasso.load(imageUrl)
                .into(imageValue);
    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void showProgress() {
        hudLoader.show();
    }

    @Override
    public void hideProgress() {
        hudLoader.dismiss();
    }
}
