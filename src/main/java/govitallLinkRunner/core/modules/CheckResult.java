package govitallLinkRunner.core.modules;

public class CheckResult {

    private String status = "";
    private Integer checkCount = 0;
    private Integer failCount = 0;
    private Integer passCount = 0;
    private Integer blockCount = 0;

    private String message = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    public void addCheckCount(Integer checkCount) {
        this.checkCount += checkCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public void addFailCount(Integer failCount) {
        this.failCount += failCount;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public void addPassCount(Integer passCount) {
        this.passCount += passCount;
    }

    public Integer getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(Integer blockCount) {
        this.blockCount = blockCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addMessage(String message) {
        this.message += message;
    }
}
