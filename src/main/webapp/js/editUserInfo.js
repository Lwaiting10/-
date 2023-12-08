const regExpChinese = /[\u4e00-\u9fa5]/;

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
    // 检查用户名不能为中文
    if (regExpChinese.test(document.getElementById("username").value)) {
        alert('用户名不能为中文');
        // 阻止表单提交
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

function handleFileSelect() {
    var fileInput = document.getElementById('headSculpture');
    var previewImg = document.getElementById('preview');

    var file = fileInput.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
            previewImg.src = e.target.result;
        };
        reader.readAsDataURL(file);
    } else {
        previewImg.src = '#';
    }
}