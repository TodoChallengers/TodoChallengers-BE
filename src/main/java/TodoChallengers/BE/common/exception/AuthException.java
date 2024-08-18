package TodoChallengers.BE.common.exception;

import TodoChallengers.BE.common.util.ResponseCode;

public class AuthException extends BaseException {

    public AuthException(ResponseCode responseCode) {
        super(responseCode);
    }
}
