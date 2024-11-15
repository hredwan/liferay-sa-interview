package com.hredwan.registration.admin.portlet;

import com.hredwan.registration.admin.constants.RegistrationAdminPortletKeys;
import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {
                "panel.app.order:Integer=0",
                "panel.category.key=" + PanelCategoryKeys.APPLICATIONS_MENU_APPLICATIONS_CONTENT
        },

        service = PanelApp.class
)

public class RegistrationPanelApp extends BasePanelApp {

    @Reference(target = "(javax.portlet.name=" + RegistrationAdminPortletKeys.REGISTRATIONADMIN + ")", unbind = "-")
    private Portlet _portlet;
    @Override
    public Portlet getPortlet() {
        return this._portlet;
    }

    @Override
    public String getPortletId() {
        return RegistrationAdminPortletKeys.REGISTRATIONADMIN;
    }

}
