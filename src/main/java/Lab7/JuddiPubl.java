package Lab7;

import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class JuddiPubl {

    private static UDDISecurityPortType security = null;
    private static UDDIPublicationPortType publish = null;

    private AuthToken authToken;

    public JuddiPubl(String userName, String password) {
        try {
            UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
            Transport transport = uddiClient.getTransport("default");

            security = transport.getUDDISecurityService();
            publish = transport.getUDDIPublishService();

            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID(userName);
            getAuthTokenMyPub.setCred(password);
            authToken = security.getAuthToken(getAuthTokenMyPub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String publish(String businessName, String serviceName, String wsdlUrl) {
        try {

            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue(businessName);
            myBusEntity.getName().add(myBusName);

            SaveBusiness sb = new SaveBusiness();
            sb.getBusinessEntity().add(myBusEntity);
            sb.setAuthInfo(authToken.getAuthInfo());
            BusinessDetail bd = publish.saveBusiness(sb);
            String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();

            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(serviceName);
            myService.getName().add(myServName);

            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(wsdlUrl);
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();

            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

            myService.setBindingTemplates(myBindingTemplates);

            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(authToken.getAuthInfo());
            ServiceDetail sd = publish.saveService(ss);
            String myServKey = sd.getBusinessService().get(0).getServiceKey();

            security.discardAuthToken(new DiscardAuthToken(authToken.getAuthInfo()));
            System.out.println("Success!");
            return myServKey;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
