package UI;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
/**
 * To be used for displaying view for my details, is destroyed after is viewed
 */
public class DetailsBean {
    int id;
    String name;
    int noOfMessages;


}
