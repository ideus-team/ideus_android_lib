package biz.ideus.ideuslibexample.injection.components;


import biz.ideus.ideuslibexample.injection.modules.ActivityModule;
import biz.ideus.ideuslibexample.injection.modules.ViewModelModule;
import biz.ideus.ideuslibexample.injection.scopes.PerActivity;
import biz.ideus.ideuslibexample.ui.chat_screen.activity.ChatActivity;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;
import biz.ideus.ideuslibexample.ui.tutorial_screen.activity.TutorialActivity;
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
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, ViewModelModule.class})
public interface ActivityComponent {
   // void inject(StartActivity activity);
   void inject(TutorialActivity activity);
   void inject(MainActivity activity);
    void inject(StartActivity activity);
    void inject(ChatActivity activity);
}
