package sharehandong.sharehandongapiserver.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(email + "NotFoundExecption");
    }
}
