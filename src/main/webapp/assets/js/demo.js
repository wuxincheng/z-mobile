$(window).load(function(){
    function notify(message, type){
        $.growl({
            message: message
        },{
            type: type,
            allow_dismiss: false,
            label: 'Cancel',
            className: 'btn-xs btn-inverse',
            placement: {
                from: 'top',
                align: 'center'
            },
            delay: 5000,
            animate: {
                    enter: 'animated fadeIn',
                    exit: 'animated fadeOut'
            },
            offset: {
                x: 20,
                y: 300
            }
        });
    };
    
    if (!$('.login-content')[0]) {
        // notify('欢迎回到嘉实财富柜台系统', 'inverse');
    } 
});