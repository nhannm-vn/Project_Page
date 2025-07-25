/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.util;

/**
 *
 * @author ADMIN
 */
public class MyApplicationConstants {
    public class DispatchFeature {
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_CONTROLLER = "searchController";
        public static final String DELETE_CONTROLLER = "deleteController";
        public static final String UPDATE_CONTROLLER = "updateController";
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "logoutController";
        public static final String CHECK_ACCOUNT_CONTROLLER = "checkAccountController";
    }
    
    public class LoginFeature {
        public static final String SEARCH_PAGE = "searchPage";
        public static final String INVALID_PAGE = "invalidPage";
    }
    
    public class LogoutFeature {
        public static final String LOGIN_PAGE = "";
    }
    
    public class SearchFeature {
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_RESULT = "searchPage";
    }
    
    public class DeleteFeature{
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class UpdateFeature{
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class CheckAccountFeature{
        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "searchPage";
    }
    
    public class CreateFeature{
        public static final String CREATE_ACCOUNT_PAGE = "createAccountPage";
        public static final String LOGIN_PAGE = "";
        public static final String USERNAME_LEN_ERR = "usernameLengthErr";
        public static final String PASSWORD_LEN_ERR = "passwordLengthErr";
        public static final String CONFIRM_NOT_MATCH_PASSWORD = "confirmPasswordNotMatch";
        public static final String FULLNAME_LEN_ERR = "fullnameLengthErr";
    }
}
