public class PasswordVerifier {
  public boolean checkMinimumCharacters(String string){
    if(string.length() >= 8){
      return true;
    }    return false;
  }
  public boolean checkDoesNotContainUsername(String pwd, User user){
    if(!pwd.contains(user.getUserName())){
      return true;
    }else{
      return false;
    }
  }
}