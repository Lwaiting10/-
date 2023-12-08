const regExpChinese = /[\u4e00-\u9fa5]/;

function validateForm() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    // 检查用户名和密码是否为空
    if (username.trim() === '' || password.trim() === '') {
        // 阻止表单提交
        return false;
    }

    // 检查用户名不能为中文
    if (regExpChinese.test(username)) {
        alert('用户名不能为中文');
        // 阻止表单提交
        return false;
    }
    // 允许表单提交
    return true;
}

document.getElementById('switch').addEventListener('click', function (event) {
    event.preventDefault(); // 阻止默认的表单提交行为
    let title = document.querySelector('.title');
    let loginBtn = document.getElementById('loginBtn');
    let rememberMeLabel = document.querySelector('.Remember');
    let switchBtn = document.getElementById('switch');
    let form = document.querySelector('form');
    if (title.innerText === 'Login') {
        title.innerText = 'Register';
        loginBtn.value = '注册';
        rememberMeLabel.style.opacity = '0';
        switchBtn.innerText = '去登录';
        form.action = 'register';
        document.getElementById('username').value = '';
        document.getElementById('password').value = '';
        document.getElementById('username').placeholder = '添加用户名';
        document.getElementById('password').placeholder = '添加密码';
        document.getElementById('password').type = 'password';
    } else {
        title.innerText = 'Login';
        loginBtn.value = '登录';
        rememberMeLabel.style.opacity = '1';
        switchBtn.innerText = '去注册';
        form.action = 'login';
        document.getElementById('username').placeholder = '用户名';
        document.getElementById('password').placeholder = '密码';
        document.getElementById('password').type = 'password';
    }
});

function changeCaptcha() {
    // 获取验证码图片元素
    var captchaImage = document.getElementById("captchaImage");

    // 修改验证码图片的 src 属性，添加一个随机参数确保每次请求都是新的
    captchaImage.src = "kaptcha.jpg?" + new Date().getTime();
}