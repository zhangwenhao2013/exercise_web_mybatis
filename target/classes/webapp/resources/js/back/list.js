/**
 * 批量删除
 */
function deleteBatch(basePath) {
    console.info("xxxxxxx")
    $("#mainForm").attr("action", basePath + "DeleteBatch.action");
    $("#mainForm").submit();
}