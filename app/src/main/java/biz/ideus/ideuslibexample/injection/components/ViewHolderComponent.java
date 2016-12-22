package biz.ideus.ideuslibexample.injection.components;

import biz.ideus.ideuslibexample.injection.modules.ViewHolderModule;
import biz.ideus.ideuslibexample.injection.modules.ViewModelModule;
import biz.ideus.ideuslibexample.injection.scopes.PerViewHolder;
import dagger.Component;

@PerViewHolder
@Component(dependencies = AppComponent.class, modules = {ViewHolderModule.class, ViewModelModule.class})
public interface ViewHolderComponent {
   // void inject(CountryViewHolder viewHolder);
}
