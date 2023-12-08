
function validateForm() {
    // 获取所有输入框
    let inputs = document.querySelectorAll('input[type="text"]');

    // 遍历检查每个输入框的值是否为空
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value.trim() === '') {
            alert('所有输入框都必须填写');
            return false;
        }
    }
    // 验证价格是否是合法数字
    let priceInput = document.getElementById('price');
    if (!/^\d+(\.\d{1,2})?$/.test(priceInput.value)) {
        alert('价格格式不合法');
        return false;
    }

    // 验证库存是否是大于等于0的整数
    let stockInput = document.getElementById('stock');
    if (!/^\d+$/.test(stockInput.value) || parseInt(stockInput.value, 10) < 0) {
        alert('库存必须是大于等于0的整数');
        return false;
    }
    // 如果所有输入框都不为空，返回 true 允许提交
    return true;
}

// 恢复值的函数
function restoreValue(inputElement, originalValue) {
    if (inputElement.value.trim() === '') {
        // 如果内容为空，则恢复为原始值
        inputElement.value = originalValue;
    }
}