package com.aronf.permission_lib;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class PermissionDialog extends DialogFragment implements View.OnClickListener {

    private String title;
    private String message;
    private String leftText;
    private String rightText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_permission, container);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setCancelable(false);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.dialog_title);
        TextView message = view.findViewById(R.id.dialog_message);
        title.setText(this.title);
        message.setText(this.message);
        Button leftButton = view.findViewById(R.id.dialog_cancel);
        Button rightButton = view.findViewById(R.id.dialog_confirm);
        leftButton.setText(leftText);
        rightButton.setText(rightText);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);

    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public PermissionDialog setTitle(String title) {
        this.title = title.isEmpty() ? "提示" : title;
        return this;
    }

    /**
     * 设置内容
     *
     * @param message 内容
     */
    public PermissionDialog setMessage(String message) {
        this.message = message.isEmpty() ? "权限已被禁用，是否前往设置?" : message;
        return this;
    }

    public PermissionDialog setLeftText(String text) {
        this.leftText = text.isEmpty() ? "取消" : text;
        return this;
    }

    public PermissionDialog setRightText(String text) {
        this.rightText = text.isEmpty() ? "确定" : text;
        return this;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_cancel) {
            onClickListener.onNegativeButton();
        } else if (v.getId() == R.id.dialog_confirm) {
            onClickListener.onPositiveButton();
        }
        dismiss();
    }

    private PermissionDialogListener onClickListener;


    public PermissionDialog setOnClickListener(PermissionDialogListener listener) {
        onClickListener = listener;
        return this;
    }
}
