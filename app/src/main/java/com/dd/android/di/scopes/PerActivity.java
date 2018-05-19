package com.dd.android.di.scopes;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 57248 on 2016/8/12.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
