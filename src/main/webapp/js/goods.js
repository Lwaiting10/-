function validateForm() {
    let goodsName = document.getElementById('goodsName').value;
    // 检查用户名和密码是否为空
    if (goodsName.trim() === '') {
        // 阻止表单提交
        alert("不能为空");
        return false;
    }
    // 允许表单提交
    return true;
}

function confirmDelete(goodsId) {
    let result = confirm("确定要删除该商品吗？");
    // 如果用户点击了确认按钮，则执行删除操作
    if (result) {
        // 执行删除操作
        window.location.href = "deleteGoods?id=" + goodsId;
    }
    // 点击取消按钮，不执行删除操作
}