function validateForm() {
    let classifyName = document.getElementById('classifyName').value;
    if (classifyName.trim() === '') {
        alert("不能为空");
        return false;
    }
    // 允许表单提交
    return true;
}

function confirmDelete(classifyId) {
    let result = confirm("删除分类，分类下的所有商品也将随之删除!确定要删除吗？");
    // 如果用户点击了确认按钮，则执行删除操作
    if (result) {
        // 执行删除操作
        window.location.href = "deleteClassify?id=" + classifyId;
    }
    // 点击取消按钮，不执行删除操作
}

function restoreValue(inputElement, originalValue) {
    if (inputElement.value.trim() === '') {
        // 如果内容为空，则恢复为原始值
        inputElement.value = originalValue;
    }
}