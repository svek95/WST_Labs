package Lab7;

import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;

import java.util.List;


public class JuddiBrows {

    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;

    private AuthToken authToken;

    public JuddiBrows(String userName, String password) {
        try {
            UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
            Transport transport = uddiClient.getTransport("default");
            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();
            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID(userName);
            getAuthTokenMyPub.setCred(password);
            authToken = security.getAuthToken(getAuthTokenMyPub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callService(String serviceName, String serviceKey) {
        try {
            FindService fs = new FindService();
            fs.setAuthInfo(authToken.getAuthInfo());
            fs.setFindQualifiers(new FindQualifiers());
            fs.getFindQualifiers().getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

            Name searchname = new Name();
            searchname.setValue("%" + serviceName);
            fs.getName().add(searchname);
            ServiceList serviceList = inquiry.findService(fs);

            GetServiceDetail gsd = new GetServiceDetail();
            gsd.setAuthInfo(authToken.getAuthInfo());
            gsd.getServiceKey().add(serviceKey);
            ServiceDetail sd = inquiry.getServiceDetail(gsd);

            if (sd == null || sd.getBusinessService() == null || sd.getBusinessService().isEmpty()) {
                System.out.println("Can not find service with key " + serviceKey);
                return;
            }

            List<BusinessService> services = sd.getBusinessService();
            BusinessService businessService = services.get(0);
            BindingTemplates bindingTemplates = businessService.getBindingTemplates();
            if (bindingTemplates == null || bindingTemplates.getBindingTemplate().isEmpty()) {
                System.out.println("No binding template for service with key " + serviceKey);
                return;
            }
            for (BindingTemplate bindingTemplate : bindingTemplates.getBindingTemplate()) {
                AccessPoint accessPoint = bindingTemplate.getAccessPoint();
                System.out.println(accessPoint.getValue());
            }

            security.discardAuthToken(new DiscardAuthToken(authToken.getAuthInfo()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
