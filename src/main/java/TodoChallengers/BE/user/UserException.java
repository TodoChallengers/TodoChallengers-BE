package TodoChallengers.BE.user;

import TodoChallengers.BE.common.exception.BaseException;
import TodoChallengers.BE.common.util.ResponseCode;

public class UserException extends BaseException {

    public UserException(ResponseCode responseCode) {
        super(responseCode);
    }
}
