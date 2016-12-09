package biz.ideus.ideuslibexample.injection.components;

import biz.ideus.ideuslibexample.injection.modules.FragmentModule;
import biz.ideus.ideuslibexample.injection.modules.ViewModelModule;
import biz.ideus.ideuslibexample.injection.scopes.PerFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment.HomeFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.image_viewer_fragment.ImageViewerFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment.PeopleFragment;
import biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment.SettingsFragment;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.forgot_password_fragment.ForgotPasswordFragment;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.sign_up_fragment.SignUpFragment;
import biz.ideus.ideuslibexample.ui.start_screen.fragments.terms_of_service_fragment.TermsOfServiceFragment;
import biz.ideus.ideuslibexample.ui.tutorial_screen.fragments.tutorial_fragments.BaseTutorialFragment;
import dagger.Component;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
@PerFragment
@Component(dependencies = AppComponent.class, modules = {FragmentModule.class, ViewModelModule.class})
public interface FragmentComponent {
    void inject(TermsOfServiceFragment fragment);
   void inject(SignUpFragment fragment);
   void inject(ForgotPasswordFragment fragment);
   void inject(BaseTutorialFragment fragment);
    void inject(HomeFragment fragment);
    void inject(PeopleFragment fragment);
    void inject(SettingsFragment fragment);
    void inject(ImageViewerFragment fragment);
}
