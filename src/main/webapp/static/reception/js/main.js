/**@preserve
 *
 * Ogilvy Taiwan main.js
 *
 * @version 20170123
 *
 */


// --------------------------------------------
// Base
// --------------------------------------------

var main = window.main || {};
main.device = {};
main.device.ua = (navigator.userAgent||navigator.vendor||window.opera).toLowerCase();
main.device.ie = (navigator.appName == 'Microsoft Internet Explorer'&&/MSIE ([0-9]{1,}[\.0-9]{0,})/.exec(navigator.userAgent)!=null)?parseFloat(RegExp.$1):99; // IE6~10 detection, non-IE browser gets 99
document.documentElement.className += ((main.device.ie <= 8)?' lte-ie8':'');
document.documentElement.className += ((main.device.ie <= 9)?' lte-ie9':'');
main.device.mobile = /(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(main.device.ua)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(main.device.ua.substr(0,4));
document.documentElement.className += (main.device.mobile?' mobile':' no-mobile');
main.device.hasTouchEvent = !!(('ontouchstart' in window) || window.DocumentTouch && document instanceof DocumentTouch);
document.documentElement.className += (main.device.hasTouchEvent?' touchevent':' no-touchevent');
document.documentElement.className += (!('opacity' in document.body.style)?' no-opacity':'');
main.device.iosSafari = (/iphone|ipod|ipad/.test(main.device.ua) && /safari\//.test(main.device.ua) && Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0);
main.mobileBreakpoint = 960;

;(function(){for(var a,e=function(){},b="assert clear count debug dir dirxml error exception group groupCollapsed groupEnd info log markTimeline profile profileEnd table time timeEnd timeline timelineEnd timeStamp trace warn".split(" "),c=b.length,d=window.console=window.console||{};c--;)a=b[c],d[a]||(d[a]=e)})();
window.log = (1==2 && main.device.ie>9 && !!Function.prototype.bind)?console.log.bind(window.console):function(){};

main.getPageY = function() {
    // Most browser has pageYOffset except IE-
    // to support legacy IEs in qurickmode or wrong doctype
    return window.pageYOffset || ((('clientHeight' in document.documentElement))?document.documentElement:document.body).scrollTop;
};
main.getWinH = function() {
    return window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight||$(window).height();
}
main.getWinW = function() {
    return window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth||$(window).width();
}

// Enable FastClick
if('addEventListener' in document) {
    document.addEventListener('DOMContentLoaded', function() { FastClick.attach(document.body); }, false);
}

/** Disable dragging of a ghost image
 */
$.fn.undraggable = function() {
    return this.each(function(){
        $(this).attr('draggable', false).css('user-select','none');
    });
};
$(function(){
    $('.row-side img, .logo').undraggable();
});

/** Avoid touchmove event propagating from scrollable child (overflow:scroll) to window.
 * Support touch device only
 * and can't be tested on Chrome developer simulator.
 *
 * @see https://github.com/lazd/iNoBounce
 * @version 20160609
 */
$.fn.blockTouchmovePropagation = function() {
    return this.each(function(){
        this.wonb = {};
        $(this)
            .off('touchstart.wonb touchmove.wonb')
            .on('touchstart.wonb', function(e) {
                this.wonb.startY = e.originalEvent.touches ? e.originalEvent.touches[0].screenY : e.originalEvent.screenY;
            })
            .on('touchmove.wonb', function(e) {
                if( this.scrollHeight > this.offsetHeight ) {
                    this.wonb.currY = e.originalEvent.touches ? e.originalEvent.touches[0].screenY : e.originalEvent.screenY;
                    this.wonb.atTop = (this.wonb.startY <= this.wonb.currY && this.scrollTop === 0);
                    this.wonb.atBottom = (this.wonb.startY >= this.wonb.currY && this.scrollHeight - this.scrollTop === /*this.offsetHeight*/$(this).innerHeight());

                    if(this.wonb.atTop || this.wonb.atBottom) { e.preventDefault(); }
                }
                else { e.preventDefault(); }
            });
    });
}

/**
 * Custom select, radio and checkbox
 * @verison 20161226
 */
$.fn.xSelect_setValue = function(){
    return this.each(function(){
        var value = $('select', this).children(':selected').first().text();
        $('.x-select-value', this).text(value);
    });
}
$.fn.xSelect = function(){
    return this.each(function(){
        var o = $(this);

        if(!!$('.x-select-value',o)[0]) { return true; }

        $('<span class="x-select-value"></span><span class="x-select-btn"></span>').appendTo(o);
        o.css({background:'none',border:'none'}); // avoid FOUC

        o.xSelect_setValue();

        $('select', o)
        //.off('change.x focusout.x focusin.x')
            .on('focusin.x focusout.x', function(e){
                o.toggleClass('focus', e.type=='focusin');
            })
            .on('change.x focusout.x', function(){
                o.xSelect_setValue();
            });
    });
}
$.fn.xCheck = function(){
    return this.each(function(){
        var o = $(this),
            input = $(':radio, :checkbox', o),
            label = $('label', o),
            id;

        if(!!$('.x-check-border',this)[0]) { return true; }

        $('<span class="x-check-border"></span><span class="x-check-bullet"></span>').appendTo(o);

        id = input.attr('id') || 'x-check-'+(((1+Math.random())*0x10000)|0).toString(16).substring(1);
        input.attr('id', id);
        label.attr('for', id);

        $(document).on('change.x focusout.x', input, function(){ setCheckedState(input); });
        $(document).on('click.x', label, function(){ setCheckedState(input);});

        setCheckedState(input);

        function setCheckedState(input) {
            var group = input.attr('name');

            if(input.is(':radio') && group!=undefined) {
                $('[name="'+group+'"]').each(function(){
                    $(this).toggleClass('checked', $(this).is(':checked'));
                    main.device.ie<=8 && $('.x-check-bullet',$(this).parent()).css('display','none').css('display',''); // a IE8- bug fix
                })
            }
            else {
                input.toggleClass('checked', input.is(':checked'));
                main.device.ie && $('.x-check-bullet',input.parent()).css('display','none').css('display',''); // a IE8- bug fix
            }
        }
    });
}
$(function(){
    $('.x-select').xSelect();
    $('.x-check').xCheck();

    // Add .is-filled class
    $(document).on('change.chkfill focusout.chkfill', '.x-input, .x-select', function(e){
        switch(true) {
            case $(this).is('.x-input'):
                $(this).toggleClass('is-filled', $(this).val() != '');
                break;
            case $(this).is('.x-select'):
                $(this).toggleClass('is-filled', $('select',this)[0].selectedIndex > 0);
                break;
        }
    }).trigger('change.chkfill');
});

/**
 * Header layout helper
 */
main.isHeaderLayout = (function(state) {
    function _init(){
        $(window).on('resize orientationchange load', _update);
        _update();
        setTimeout(_update, 31);
    }
    function _update() {
        if(main.getWinW()<=main.mobileBreakpoint && !check('mb')) {
            $('html').removeClass('header-pc').addClass('header-mb');
            $(window).trigger('headerLayout', ['mb']);
        }
        if(main.getWinW()>main.mobileBreakpoint && check('mb')) {
            $('html').removeClass('header-mb').addClass('header-pc');
            $(window).trigger('headerLayout', ['pc']);
        }
    }
    function check(state) {
        return $('html').hasClass('header-'+state);
    }
    $(_init);
    return check;
})();

main.updateBodyHeight = (function(){
    function _init() {
        $(window).on('resize orientationchange load', update);
        update();
        setTimeout(update, 31);
    }
    function update() {
        //if(main.isHeaderLayout('mb')) return true;
        $('.mastbody-inner').css('height', main.getWinH());
    }
    $(_init);
    return update;
})();

/**
 * Global resize renderer
 */
main.resizeWith = (function(fn){
    var fns = [];
    function _init() {
        $(window).on('resize orientationchange', _render);
        _render();
        setTimeout(_render, 11);
        setTimeout(_render, 1111);
    }
    function _render(fn) {
        $.each(fns, function(index, fn){
            fn();
        });
    }
    function resizeWith(fn) {
        if($.isFunction(fn)) {
            fns.push(fn);
        }
    }
    $(_init);
    return resizeWith;
})();


/**
 * SVG Spinner
 * @20161212
 */
$.fn.spinner = function(){
    var args = arguments;
    return this.each(function(){
        var el = $(this).empty(),
            elCircle,
            opts = (typeof args[0]==='object')?args[0]:{},
            w = opts.width||36,
            strokeWidth = opts.strokeWidth||3,
            wCircle = (w - strokeWidth*2),
            speed = 2000,
            circ = Math.round(wCircle* Math.PI); // 圓周長
        if(args[0] == 'stop') {
            el.stop().css({'width':0,'height':0})
            return true;
        }
        el.css({ 'width':w, 'height':w}).append('<svg width="100%" height="100%"><circle cx="'+w*.5+'px" cy="'+w*.5+'px" r="'+wCircle*.5+'px"></circle></svg>');
        $('svg', el).css({ 'display': 'block', 'fill': 'none' });
        elCircle = $('circle', el);
        elCircle.css({ 'stroke-dasharray': circ, 'stroke-width': strokeWidth });

        function _loop() {
            el.stop().css('text-indent', 0);
            el.animate({ 'text-indent': -360 }, {
                easing: 'linear',
                duration: speed,
                step: function(now, fx) {
                    el.css({
                        '-webkit-transform': 'rotate('+now+'deg)',
                        transform: 'rotate('+now+'deg)'
                    });
                    elCircle.css('stroke-dashoffset', (Math.abs(now)/360*circ*2)+'px');
                },
                done: _loop
            });
        }
        _loop();
    });
};

main.preloadControl = (function(){
    var allDone = false,
        pageDone = false,
        delay = 1.2, // a delay (ms) to hide loader screen
        timer;

    function _init() {
        show();

        setTimeout(_loaded, 10000); // idle
        $('body').add('<img src="">').imagesLoaded(_pageLoaded);
    }
    function show() {
        TweenMax.to('#loader', .168, {autoAlpha:1});
        $('#loader .spinner').spinner();
    }
    function hide() {
        TweenMax.to('#loader', .3, {autoAlpha:0, onComplete:function(){
                $('#loader .spinner').spinner('stop');
            }});
    }
    function _pageLoaded(e) {
        pageDone = true;
        //log('_pageLoaded'/*, e.images*/);

        // Continue preload other asset
        // by detecting element existed
        switch(true) {
            case main.device.ie>8 && !!$('#splashTagline')[0] && !!$('#splashRhyme')[0] && !!$('#splashSignature')[0]:
                timer = setInterval(function(){
                    if(window.introStarted == true) {
                        clearInterval(timer);
                        setTimeout(_loaded, delay*1000);
                    }
                }, 111);
                break;
            default:
                setTimeout(_loaded, delay*1000);
                break;
        }
    }
    function _loaded() {
        if(allDone) { return; }
        allDone = true;
        clearInterval(timer);
        hide();
    }


    // Only preload once on the same section
    if(location.hash.indexOf(Cookies.get('section')) == -1) {
        $(_init);
    }
    // Avoid unnecessary preload screen
    else {
        document.getElementById('loader').style.display = 'none';
    }
    return {
        show: show,
        hide: hide
    }
})();


// --------------------------------------------
// Navbar
// --------------------------------------------
main.navBar = (function(){
    var navbar, submenu, navoverlay, navtoggle, dash,
        timeout, _log = (1==2)&&main.device.ie>9&&!!Function.prototype.bind?(console.log.bind(window.console)):function(){};

    function _init() {
        navbar = $('#navBar');
        if(!navbar[0]) return;

        navoverlay = $('<div id="navOverlay" class="navoverlay"></div>').insertBefore(navbar);
        navtoggle = $('<div id="navToggle" class="navtoggle"><b><i></i><i></i><i></i></b></div>').insertAfter(navbar);
        dash = $('.dash', navbar);



        // Handle navbar toggle (mb)
        // ----------------------------------------------
        navbar.blockTouchmovePropagation();
        navoverlay.blockTouchmovePropagation();
        navoverlay.on('click', function(){
            toggleNavbar(false);
        });
        navtoggle.on('click', function(){
            toggleNavbar(!isNavbarOpen());
        });


        // Handle submenu
        // ----------------------------------------------
        navbar.on('click','.mainbtn', function(e){
            if(main.isHeaderLayout('pc')) return true;
            var m = $(this).parent(),
                s = $('.submenu',m);
            if(!!s[0]) {
                e.preventDefault();
                if(!m.hasClass('active')) {
                    openSubmenu(m);
                } else {
                    closeSubmenu(m);
                }
            }
        });
        // Click subbtn and close navbar
        navbar.on('click', '.subbtn', function(e){
            if(main.isHeaderLayout('mb')) {
                if($(this).attr('href')!='#' && $(this).attr('href')!='javascript:;') {
                    toggleNavbar(false);
                }
            }
        });
        navbar.hoverIntent({
            selector: '.mainbtn',
            over: function(e){
                if(main.isHeaderLayout('mb')) return;
                openSubmenu($(this).parent());
            },
            out: function(){
                //closeSubmenu(0);
            },
            timeout: 200, // delay before the "out" function is called.
            sensitivity: 2, // mouse travels fewer than this number of pixels between polling intervals, then the "over" function will be called.
            interval: 31 //The soonest the "over" function can be called is after a single polling interval.
        });
        $(document).on('mousemove.submenu click.submenu', function(e){
            if(main.isHeaderLayout('mb')) return true;
            var m = $('.mainitem.active', navbar),
                s = $('.submenu', m);
            if(!s.find(e.target)[0] && !s.is(e.target) && !m.find(e.target)[0] && !m.is(e.target)) {
                closeSubmenu(m);
            }
        });


        // Dash line effect (pc)
        // ----------------------------------------------
        navbar.on('mouseenter click', '.mainbtn', function(e){
            if(main.isHeaderLayout('mb')) return true;
            var m = $(this).parent();
            dashlineTo(m, 0);
        });
        navbar.on('mouseleave', function(e){
            if(main.isHeaderLayout('mb')) return true;
            var m = $('.mainitem.on', navbar);
            dashlineTo(m, .3);
        });

        main.resizeWith(function(){
            var m = $('.mainitem.on', navbar);
            dashlineTo(m, 0);
        });

        // Initial state
        /*if(main.isHeaderLayout('pc')) {
            TweenMax.fromTo(dash, .7, {alpha:0},{alpha:1});
        }*/

        function dashlineTo(m, delay) {
            TweenMax.killTweensOf(dash, {left:true, width:true});
            if(!m[0]) { //default position
                TweenMax.to(dash, .4, {delay:delay,
                    left: navbar.find('.mainitem').first().position().left - main.getWinW()*.08,
                    width: main.getWinW()*.06,
                    ease:Quart.easeInOut});
            } else {
                TweenMax.to(dash, .4, {delay:delay,
                    left: m.position().left + parseInt(m.css('margin-left')),
                    width: m.width(),
                    ease:Quart.easeOut});
            }
        }





        // Responsive update
        // ----------------------------------------------

        $(window).on('headerLayout', function(e, state){
            //$('.mainitem', navbar).removeClass('active');
            if(state == 'pc') {
                toggleNavbar(false);
                //$('.submenu', navbar).css('display','');
                dashlineTo($('.mainitem.on', navbar), .6);
            }
            if(state == 'mb') {
                //$('.mainitem.on .mainbtn', navbar).trigger('click');
            }
        });

        if(main.isHeaderLayout('mb')) {
            //$('.mainitem.on .mainbtn', navbar).trigger('click');
        }
    }


    // ----------------------------------------------
    function toggleNavbar(flag) {
        if(flag && !isNavbarOpen()) {
            navbar.removeClass('out').addClass('in');
            navoverlay.removeClass('out').addClass('in');
            $('html').addClass('navbar-open');

            setTimeout(function(){ navbar.scrollTop(0); }, 30);
        }
        if(!flag && isNavbarOpen()) {
            navbar.removeClass('in').addClass('out');
            navoverlay.removeClass('in').addClass('out');
            setTimeout(function(){
                $('html').removeClass('navbar-open');
            }, 400);
        }
    }
    function isNavbarOpen() {
        return $('html').hasClass('navbar-open');
    }



    // ----------------------------------------------
    /*function isSubmenuOpen() {
        return $('html').hasClass('submenu-open');
    }*/
    function openSubmenu(m) {
        if(/*isSubmenuOpen() && */$('.mainitem.active', navbar).is(m)) return;

        var s = $('.submenu', m);
        if(!s[0]) return;
        if(main.isHeaderLayout('pc')) {
            //$('.masthead').css('z-index', 10);
            s.fadeIn(168, 'easeOutQuad', function(){
                m.css('height', s.height() + $('.mainbtn',m).height()/* + parseInt(s.css('padding-bottom'))*/);
            });
        }
        else { // mobile
            s.slideDown(168, 'easeOutQuad');
        }

        m.addClass('active');//.siblings('.active').removeClass('active');

        //$('html').addClass('submenu-open');
        _log('openSubmenu');
    }
    function closeSubmenu(m) {
        var s = $('.submenu', m);
        if(!s[0]) return;

        //$('.masthead').css('z-index', '');
        //$('.mainitem', navbar).removeClass('active');
        m.removeClass('active');
        //$('html').removeClass('submenu-open');
        if(main.isHeaderLayout('pc')) {
            s.fadeOut(100, 'easeOutQuad', function(){
                m.css('height', '');
            });
        } else {
            s.slideUp(168, 'easeOutQuad');
        }

        _log('closeSubmenu');
    }

    $(_init);
    return {
        isNavbarOpen: isNavbarOpen,
        toggleNavbar: toggleNavbar,
        //isSubmenuOpen: isSubmenuOpen,
        openSubmenu: openSubmenu,
        closeSubmenu: closeSubmenu
    }
})();





// --------------------------------------------
// Section control
// Deal with hash path by hashchange event
// Support <a href="#/HASH_PATH">, <div data-path="#/HASH_PATH">, or change window.location directly
// --------------------------------------------
main.sectionControl = (function(){
    var hash,
        sectionId,
        subpageId,
        lastSection,
        currSection,

        lastSubpage,
        currSubpage,

        _log = (1==2)&&main.device.ie>9&&!!Function.prototype.bind?(console.log.bind(window.console)):function(){};


    function init() {

        if(!$('[data-section], [data-mainpage]')[0]) { return; }

        // http://benalman.com/projects/jquery-hashchange-plugin/
        $(window).hashchange(_hashchange);

        var startpath = $('[data-startpath]').attr('data-startpath');

        // e.g media.html with data-startpath defined
        if(location.hash != startpath && !!startpath && location.hash=='') {
            hash = $('[data-startpath]').attr('data-startpath');
            setHash(hash);
        }
        // Deal with ajax hash path
        // e.g media.html#/article/
        else if(_canAjax()) {
            //_log('ajaxPage call(A)');
            ajaxPage(location.hash.substr(2).split('/')[1], _hashchange);
        }
        else {
            _hashchange();
        }


        $(document).on('click', '[data-path]', function(){
            setHash($(this).attr('data-path'));
        });

        $(window).on('mousewheel', _mousewheel);
        //$(document).on('touchstart touchend touchmove',_swipe);

        $('[data-mainpage="list"], [data-subpage]').blockTouchmovePropagation();

        /** Ajax section
         */

        // Ajax link with data-ajaxpath and href containing '#/article/ID'
        $(document).on('click', '[data-section="article"] [data-articleid][href]', function(e){
            e.preventDefault();
            var _hash = $(this).attr('data-ajaxpath');
            //_log('ajaxPage call(B)');
            var id = $(this).attr('data-articleid');
            ajaxPage(id, function(){
                setHash('#/article/'+id);
            })
        });

        // Ajax first list (page-one) for article page
        if(!!$('[data-listurl]')[0] && !$('[data-mainpage="list"] .content')[0]) {
            var url = $('[data-listurl]').attr('data-listurl');
            ajaxList(url);
        }

        // Ajax next page
        var page = 0;
        $(document).on('click', '[data-appendlist=btn-more]', function (e) {
            e.preventDefault();
            var count = $(this).attr('data-count');
            page++;
            var url = $(this).attr('href')
            if (url.indexOf("?") != -1) {
                var ary = url.split("?")[0];
                url = ary + "?page=" + page;
            }
            ajaxList(url, function () {
                // Scroll to bottom
                var s = main.getActiveSectionPage().filter('[data-mainpage="list"]').find('[data-scrollpane]');
                if (!!s[0] && s.data('jsp')) {
                    setTimeout(function () { s.data('jsp').scrollByY(366, true); }, 111);
                }
            });
            if (page == (count - 1))
                $('#pagelist').hide();
        });
    }
    function _canAjax() {
        return !!(location.hash.indexOf('#/article/') == 0 && location.hash.length > '#/article/'.length && !!$('[data-articleurl]')[0]);
    }
    function ajaxPage(id, cb) {
        var url = $('[data-articleurl]').attr('data-articleurl').replace(/{id}/, id);

        _log('ajaxPage url =>', url, id);

        if(!!$('[data-subpage="'+id+'"]', currSection)[0]) {
            console.info('Subpage existed, do not ajax');
            $.isFunction(cb) && cb();
        }
        else {
            main.preloadControl.show(); //!!!

            $('<div></div>').load( url + ' #ajaxdetail', function(e) {
                var el = $(this).find('#ajaxdetail');

                _log('ajax page loaded', el[0]);

                if(!!el[0]) {
                    el.removeAttr('id');
                    el.add('<img src="">').imagesLoaded(function(){
                        $('[data-section="article"] [data-mainpage="detail"]').append(function(){
                            // Create holder with subpageId to avoid unwanted itinerated ajax
                            return $('<section data-subpage="'+id+'" class="subpage"></section>').append(el);
                        });

                        $.isFunction(cb) && cb();
                        //main.getActiveSectionPage().updateContentSize();
                        $(document).trigger('ajaxPageLoaded');

                        TweenMax.delayedCall(1, function(){
                            main.preloadControl.hide();
                        });
                    });
                }
                else {
                    console.info('No page found');
                    main.preloadControl.hide();
                }
            });
        }
    }
    function ajaxList(url, cb) {

        _log('ajaxList url =>', url);

        $('<div></div>').load( url + ' #ajaxlist', function(e) {
            var el = $(this).find('#ajaxlist'),
                content;

            if(!!el[0]) {
                el.removeAttr('id');
                el.add('<img src="">').imagesLoaded(function(e2){

                    _log('ajax list loaded', e2.images);

                    // Initailize list
                    if(!$('[data-mainpage="list"] .content')[0]) {
                        content = el.html();
                        $('#ajaxlist').append(content);
                        $(document).trigger('ajaxListLoaded');
                        $.isFunction(cb) && cb();
                    }

                    // Update list
                    else {
                        // Works section
                        if(!!$('#works')[0]) {
                            var list1 = $('<div class="worklist"></div>');

                            content = el.find('.worklist').html();

                            $('#ajaxlist .worklist').after(list1).remove();
                            list1.hide().append(content);
                            //list1.add('<img src="">').imagesLoaded(function(){
                            list1.fadeIn(168);
                            $(document).trigger('ajaxListLoaded');
                            $.isFunction(cb) && cb();
                            //});
                        }
                        // Media, events, viewports
                        else {
                            content = el.find('.newslist').html();
                            $('#ajaxlist .newslist').append(content);
                            $(document).trigger('ajaxListLoaded');
                            $.isFunction(cb) && cb();
                        }
                    }
                });
            }
        });
    }

    function _hashchange(e) {
        //_log('_hashchange');
        hash = location.hash;
        _internalChange();
    }

    function _internalChange() {
        var _hash;

        // e.g  about.html  or about.html#/
        if(hash.indexOf('#/') != 0 || (hash.indexOf('#/') == 0 && hash.length == 2)) {
            console.info('No path fragment found');
            _goDefault();
        }

        else {
            _hash = hash.substr(2).split('/');
            sectionId = _hash[0];
            if(_hash.length>1 && $.trim(_hash[1])!='') {
                subpageId = _hash[1];
            } else {
                subpageId = null;
            }

            _log('current internal hash => ', hash/*, 'location.hash => ', location.hash*/);

            var el = $('[data-section="'+sectionId+'"]');
            if(!el[0]) {
                console.info('Section of "' + sectionId + '" not found!');
                //console.info('Do nothing');
                _goDefault();
            }
            else {
                setSection(sectionId);

                // A section without list page
                if(!$('[data-mainpage="list"]', currSection)[0]) {
                    console.info('A section without list page, jump into first page');
                    subpageId = $('[data-subpage]', currSection).first().attr('data-subpage');
                    setPage(subpageId);
                }
                else {
                    // Enter subpage
                    if(!!subpageId) {
                        setPage(subpageId);
                        //_log('dealing with subpageId (1)');
                    }
                    // Hashchange from sub page to list page
                    else {
                        switchMainpage(false);
                        //_log('dealing with subpageId (2)');
                    }
                }
            }
        }
    };

    function _goDefault() {
        //console.info('Go to default section');
        hash = '#/' + $('[data-section]').first().attr('data-section');
        _internalChange();
    }

    var pageSpeed = .8; //!!!
    var sectionSpeed = .4; //!!!

    function setSection(id) {
        _log('setSection id=>', id);
        var el = $('[data-section="'+id+'"]'),
            dir = 1,
            distance = 50;

        lastSection = currSection;
        currSection = el;

        if(currSection.is(lastSection)) {
            //console.info('Same section, do not switch section');
            return false;
        }

        if(!!lastSection && !!lastSection[0]) {
            dir = (currSection.index('[data-section]') < lastSection.index('[data-section]')) ? -1: 1;
            TweenMax.to(lastSection, sectionSpeed,
                { alpha:0, y:dir*-distance, ease:Quad.easeInOut, clearProps:'transform,opacity'});
        }

        currSection.show().addClass('on');
        $('[data-section]').not(currSection).removeClass('on');
        TweenMax.delayedCall(sectionSpeed, function(){
            $('[data-section]').not(currSection).hide();
        });

        TweenMax.fromTo( currSection, sectionSpeed,
            { alpha:0, y:dir*distance },
            { alpha:1, y:0, ease: Quad.easeInOut, clearProps:'transform,opacity'});

        updateMenuState();

        // Only preload once on the same section
        // https://github.com/js-cookie/js-cookie
        Cookies.set('section', sectionId);
    }



    function setPage(id) {
        _log('setPage id=>', id);
        if(!currSection || !currSection[0]) { return false; }

        var el = $('[data-subpage="'+id+'"]', currSection);

        // No such sub page, go back to list page
        if(!el[0]) {
            console.info('Subpage of "' + id + '" not found!');
            // Deal with ajax page
            if(_canAjax()) {
                //_log('ajaxPage call(C)');
                ajaxPage(id, _internalChange);
            }
            else {
                subpageId = null;
                TweenMax.delayedCall(pageSpeed, function(){
                    $('[data-subpage]').removeClass('on');
                });
                switchMainpage(false);
            }
            return false;
        }

        // Go to sub page
        else {
            lastSubpage = currSubpage;
            currSubpage = el;

            currSubpage.addClass('on');
            $('[data-subpage]').not(currSubpage).removeClass('on');
            if(!!lastSubpage) { lastSubpage.toggleScrollpane(false); }
            switchMainpage(true);
        }
    }

    function switchMainpage(isDetail) {
        var btnBack = $($('.mastbody-inner > .back')[0] || $('<a class="btn-close back" href="./" title="回前頁"><i class="icn icn-close"></i></a>').appendTo('.mastbody-inner')); // Create close button for mobile

        setTimeout(function(){
            $(document).trigger('beforeSectionChange');
        }, pageSpeed*.1);

        if(isDetail) {
            $('html').addClass('ui-invert');
            TweenMax.to(currSection, pageSpeed, {x:'-100%', ease:Expo.easeInOut, onComplete:_tweenDone});
            if( !!$('.back', currSubpage)[0] ) {
                TweenMax.delayedCall(pageSpeed, function(){ btnBack.attr('href', $('.back', currSubpage).attr('href')).fadeIn(500); });
            }
        } else {
            $('html').removeClass('ui-invert');
            TweenMax.to(currSection, pageSpeed, {x:'0%', ease:Expo.easeInOut, clearProps:'transform', onComplete:_tweenDone});
            btnBack.fadeOut(168);
        }

        function _tweenDone() {
            currSection.add('<img src="">').imagesLoaded(function(){
                $(document).trigger('sectionChanged', [subpageId, currSubpage, sectionId, currSection]);
            });
        }
    }

    function updateMenuState() {
        $('#navDots [href*="#/'+sectionId+'"]').addClass('on')
            .siblings('.on').removeClass('on');
        $('#navBar [href*="#/'+sectionId+'"]').parent('.subitem').addClass('on')
            .siblings('.on').removeClass('on');
    }

    // Support gesture for iOS safari
    /*
    var touchX;
    function _swipe(e) {
        // if(main.device.iosSafari) return true;
        var x = e.originalEvent.clientX || ( e.originalEvent.touches[0]||{clientX:null}).clientX;
        switch(true) {
            case /touchstart|mousedown/.test(e.type): touchX = x; break;
            case /touchend|mouseup/.test(e.type): touchX = null; break;
            case /touchmove|mousemove/.test(e.type):
                clearTimeout(window.touchIntvId);
                window.touchIntvId = setTimeout(function(){ touchX = null; }, 18);
                if(!touchX) return true;
                //if(touchX - x > 50) { next(); touchX = null; }
                if(touchX - x < -100){ setHash(sectionId); touchX = null; }
                break;
        }
    }
    */

    // Support mousewheel
    var mwStamp= 0;
    function _mousewheel(e) {
        if(!$('#navDots')[0]) {
            return true;
        }
        if(main.getWinW() <= main.mobileBreakpoint) {
            return true;
        }
        var s = $('[data-scrollpane]', main.getActiveSectionPage());
        if(!!s[0] && (!!s.find(e.target)[0] || !!s.is(e.target))) {
            return true;
        }

        // Avoid intense mouseevents fire
        // on device like macbook touchpad
        if(!mwStamp) {
            mwStamp = new Date().getTime();
        }
        //_log(e.deltaY);
        if((Math.abs(e.deltaY) > 20 && new Date().getTime() - mwStamp)/*mili seconds*/ > 500) {

            mwStamp = new Date().getTime();
            mwCount = 0;

            var dot, u;
            if(e.deltaY < 0) {
                dot = $('#navDots .dot.on').nextAll('.dot[href*="#/"]').first();
            } else if(e.deltaY > 0) {
                dot = $('#navDots .dot.on').prevAll('.dot[href*="#/"]').first();
            }
            u = dot.attr('href');;
            if(!!dot[0] && u.indexOf('#/')>-1) {
                setHash(u.split('#/')[1]);
                _log('\t\t e.deltaY=>', e.deltaY, u);
            }
        }
    }

    function setHash(path) {
        location.href = '#/'+ path.replace('#/','');
    }


    return {
        init: init,
        setHash: setHash,
        ajaxList: ajaxList,
        getSection: function() {
            return currSection;
        },
        getSectionId: function(){
            return sectionId;
        },
        getPage: function() {
            return currSubpage;
        },
        getPageId: function() {
            return subpageId;
        }
    }
})();

/**
 * SectionControl event debug
 */
$(function(){
    $(document).on('beforeSectionChange sectionChanged ajaxPageLoaded ajaxListLoaded', function(e, pageId, currSubpage, sectionId, currSection){
        log('----------  '+e.type+'  ----------');
    });
});

/**
 * Global GA tracking
 */
//var ga = function(){} // dummy window.ga for debug

var gatrack = {
    init: function () {
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date(); a = s.createElement(o),
                m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)
        })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

        ga('create', 'UA-90116745-1', 'auto');

        $(document).on('sectionChanged', function(e, pageId, currSubpage, sectionId, currSection){
            var value, sId;
            switch(true) {
                case !!$('#works')[0]:
                    if (sectionId == "career") {
                        sId = "career";
                    } else {
                        sId = 'works';
                    }
                    break;
                default:
                    sId = sectionId;
                    break;
            }
            if(!!sId && !!pageId) {
                value = sId+'-'+pageId;
            } else if(!!sId && !pageId) {
                value = sId;
            }
            gatrack.pv(value);
        });
        $(document).on('mousedown touchstart', '[data-ga-event]', function(){
            var el = this;
            if(!el.ga_fired) {
                el.ga_fired = 1;
                setTimeout(function(){ el.ga_fired = 0; }, 100);
                gatrack.event($(this).attr('data-ga-event'));
            }
        });
    },
    pv: function(value) {
        if(!!window.ga && value != undefined && value != null && value != '') {
            console.log('GA pv=>', value);
            ga('send', 'pageview', value);
        }
    },
    event: function(value) {
        if(!!window.ga && value != undefined && value != null && value != '') {
            console.log('GA event=>', value);
            ga('send', 'event', value, 'click', value);
        }
    }
}
$(gatrack.init);



main.getActiveSectionPage = function() {
    return $('[data-mainpage="list"], .on[data-subpage]', '.on[data-section]');
}

/**
 * Update content
 */
$.fn.updateContentSize = function() {
    return this.each(function(){
        // .content[auto-size]
        $('.row-main .content[data-autosize]', this).each(function(){
            var row = $(this).parents('.row-main'),
                h = row.outerHeight()
                    - $('.header', row).outerHeight()
                    - parseInt($('.header', row).css('margin-bottom')),
                winW = main.getWinW();

            if(winW > main.mobileBreakpoint) { // pc only
                //log('h=>', h, 'page-body height=>', $(this).parents('.page-body').outerHeight());
                $(this).css('height', h);
            } else {
                $(this).css('height', 'auto');
            }
        });
        //.content-holder
        $('.row-side .content[data-autosize] .content-outer', this).each(function(){
            var h = main.getWinH() - 72/* - parseInt($(this).css('margin-top'))*/,
                winW = main.getWinW();
            if(winW > main.mobileBreakpoint) { // pc only
                $(this).css('min-height', h);
            } else {
                $(this).css('min-height', '');
            }
        });
        //[data-centered]
        $('.row-side [data-centered]', this).each(function(){
            var p = $(this).parents('.content-outer, .content-mask');
            $(this).css({
                'margin-left': Math.round((p.width() - $(this).outerWidth())*.5),
                'margin-top': Math.round((p.height() - $(this).outerHeight())*.5)
            });
        });
    });
}

/**
 * Apply/destroy scrollpane
 * http://jscrollpane.kelvinluck.com/
 * http://jscrollpane.kelvinluck.com/settings.html
 */
var scrollpaneConf = {
    autoReinitialise: true, // For content grows
    autoReinitialiseDelay: 31,
    mouseWheelSpeed: 30,
    animateEase: 'easeOutQuart', animateDuration: 168, animateScroll: false, // use only when content appended
    hideFocus: true,
    maintainPosition: true // Whether the scrollpane should attempt to maintain it's position whenever it is reinitialised.
};
$.fn.toggleScrollpane = function(bool) {
    return this.each(function(){
        $('[data-scrollpane]', this).each(function(){
            var el = $(this), api = el.data('jsp'), winW = main.getWinW();

            // pc only
            if(winW > main.mobileBreakpoint && bool!=false && !api) {
                el.jScrollPane(scrollpaneConf);
            }
            // rwd mobile only
            if((winW <= main.mobileBreakpoint || bool == false) && !!api) {
                api.destroy();
            }
        });
    });
};


/**
 * Pic carousel
 * // https://owlcarousel2.github.io/OwlCarousel2/docs/api-options.html (v2.1.0)
 */
var owlConf = {
    //startPosition: 1, // autoHeight set true make some browser autoHeight failed when element invisible
    items: 1,
    loop: true,
    nav: false, dots: true,
    fluidSpeed: 250,
    //lazyLoad: true,
    responsiveRefreshRate: 11,
    autoHeight: true
}
$.fn.setPicSlider = function() {
    return this.each(function(){
        $('[data-picslider]', this).each(function(){
            var el = $(this),
                list = $('.list', el);
            if(list.find('.item').length <= 1) {
                return true;
            }
            if(this.owl == true) {
                //list.trigger('to.owl.carousel', [0]); // and startPosition set 1
                return true;
            };
            this.owl = true;

            log('init picSlider', this);

            list.addClass('owl-carousel');
            list.owlCarousel($.extend({
                onInitialized: function(){
                    $('.owl-dots', list).appendTo(list.parent());
                    $('.owl-controls', list).hide();
                    main.getActiveSectionPage().updateContentSize();
                }
            }, owlConf));
            list.on('changed.owl.carousel', function(){
                main.getActiveSectionPage().updateContentSize();
            });
        });
    });
}

$.fn.updateFormElement = function() {
    log('updateFormElement');
    return this.each(function(){
        $('.x-select', this).xSelect();
        $('.x-check', this).xCheck();
    });
}

$(function(){
    main.resizeWith(function(){
        main.getActiveSectionPage().updateContentSize().toggleScrollpane();
    });
    $(document).on('beforeSectionChange', function(e){
        main.getActiveSectionPage().updateContentSize().toggleScrollpane();
        main.getActiveSectionPage().setPicSlider();
    });
    $(document).on('ajaxPageLoaded', function(e){
        main.getActiveSectionPage().updateContentSize();
    });
    $(document).on('beforeSectionChange ajaxListLoaded', function(){
        main.getActiveSectionPage().updateFormElement();
    });
});








// --------------------------------------------
// Page content
// --------------------------------------------

// Set default jquery.reel options
$.extend($.reel.def, { draggable: false, scrollable: false, steppable: false, throwable:false, wheelable:false, cursor:'' });


/**
 * Page effect
 * http://test.vostrel.cz/jquery.reel/docs/jquery.reel.html
 *
 * Static effect markup: 		<div class="effect-holder" data-effect="career"><img class="static" src="/static/reception/img/effect/career-static-red.png"></div>
 * Animated effect markup: 		<div class="effect-holder" data-effect="career"></div>
 */
$(function(){
    if(!$('[data-effect]')[0]) return;

    var holder = $('[data-effect]'),
        effect,
        isStatic = !!holder.find('img.static')[0],
        conf = {
            loops: true,
            speed: 0, // stop on loaded
            laziness: 1.5, // for mobile engergy saving, the lower the more smoothing
            responsive: true
        };

    // Replace with a static image on mobile
    if(main.device.mobile) {
        if(isStatic) return;

        holder.empty();
        switch(true) {
            // about section
            case holder.is('[data-effect="about"]'):
                $('<img class="static" src="/static/reception/img/effect/about-static-red.png">').appendTo(holder);
                break;

            // works section
            case holder.is('[data-effect="works"]'):
                $('<img class="static" src="/static/reception/img/effect/works-static-red.png">').appendTo(holder);
                break;

            // news section (media, events)
            case holder.is('[data-effect="news"]'):
                $('<img class="static" src="/static/reception/img/effect/news-static-red.png">').appendTo(holder);
                break;

            // viewpoints section (media, events)
            case holder.is('[data-effect="viewpoints"]'):
                $('<img class="static" src="/static/reception/img/effect/viewpoints-static-red.png">').appendTo(holder);
                break;

            // career section
            case holder.is('[data-effect="career"]'):
                $('<img class="static" src="/static/reception/img/effect/career-static-red.png">').appendTo(holder);
                break;
        }
        return;
    }

    // ----------------------------------------

    if(isStatic) {
        return;
    }

    // ----------------------------------------

    TweenMax.set(holder, {autoAlpha:0});
    switch(true) {
        // about section
        case holder.is('[data-effect="about"]'):
            $('<img class="reel effect" src="/static/reception/img/effect/about/61.png?v=1">').appendTo(holder);
            conf = $.extend(conf,{
                images: '/static/reception/img/effect/about/#.png?v=1',
                frame: 61,
                frames: 107
            });
            break;

        // works section
        case holder.is('[data-effect="works"]'):
            $('<img class="reel effect" src="/static/reception/img/effect/works/35.png?v=1">').appendTo(holder);
            conf = $.extend(conf,{
                images: '/static/reception/img/effect/works/#.png?v=1',
                frame: 35,
                frames: 121
            });
            break;

        // news section
        case holder.is('[data-effect="news"]'):
            $('<img class="reel effect" src="/static/reception/img/effect/news/88.png?v=1">').appendTo(holder);
            conf = $.extend(conf,{
                images: '/static/reception/img/effect/news/#.png?v=1',
                frame: 88,
                frames: 120
            });
            break;

        // viewpoints section
        case holder.is('[data-effect="viewpoints"]'):
            $('<img class="reel effect" src="/static/reception/img/effect/viewpoints/37.png?v=1">').appendTo(holder);
            conf = $.extend(conf,{
                images: '/static/reception/img/effect/viewpoints/#.png?v=1',
                frame: 37,
                frames: 121
            });
            break;

        // career section
        case holder.is('[data-effect="career"]'):
            $('<img class="reel effect" src="/static/reception/img/effect/career/47.png?v=1">').appendTo(holder);
            conf = $.extend(conf,{
                images: '/static/reception/img/effect/career/#.png?v=1',
                frame: 47,
                frames: 107
            });
            break;
    }


    effect = $('img.reel.effect', holder);
    if(!effect[0]) return;
    effect.add('<img src="">').imagesLoaded(initReel);

    function initReel() {
        effect.on('loaded', function(e){
            log('Page effect loaded', conf);
            TweenMax.to(holder, 2, {delay:1.5, autoAlpha:1});
            setReel();
        });
        effect.reel(conf);
        $(document).on('beforeSectionChange', setReel);
    }
    function setReel() {
        //log( 'setReel pageId', main.sectionControl.getPageId() );
        if(main.sectionControl.getPageId() != null) {
            effect.trigger('stop');
        }
        else {
            effect.reel('speed', .24) // set speed 24fps to make timeline running
                .trigger('play');
        }
    }
});


// --------------------------------------------
// Home
// --------------------------------------------

main.home = {};

main.home.initIntro = function() {
    if(!$('#splashTagline, #splashRhyme, #splashSignature')[0]) return;

    var tagline,
        taglineAnim, rhymeAnim,
        originObj = {'left':0, 'top':0},
        signature,
        signatureReel,
        pebbleReel,
        started = false,
        signDone = false,
        signAlmostDone = false,
        endingAnim;

    if(main.device.ie <= 8) {
        $('#splashRhyme').hide();
        $('#splashSignature').css('visibility', 'visible');
        $('#splashSignature .complete').css('visibility', 'visible');
        return;
    }


    $('html').addClass('intro-display');
    TweenMax.set('.masthead, .mastfoot',{autoAlpha:0, zIndex:10}); // fix fade-in bug

    // Tagline animation
    tagline = $('#splashTagline');

    taglineAnim = new TimelineMax()
        .from('#splashTagline', 1, {alpha:0, ease:Quad.easeInOut})
        .from($('.c1, .c1i, .c2, .c2i', '#splashTagline'), 2, {clip:'rect(0px, 280px,60px, 0px))', ease:Expo.easeInOut}, '-=1');

    // Rhyme animation
    CustomEase.create('easePauseAtStart', 'M0,0 C0,0 0.518,0 0.518,0 0.646,0 0.912,0.036 0.934,0.168 0.977,0.426 1,0.73 1,1');
    CustomEase.create('easePauseAtStart_SlowAtEnd', 'M0,0,C0,0,0.4,0,0.4,0,0.756,0.012,0.78,0.074,0.826,0.21,0.909,0.457,0.892,0.818,1,1');
    CustomEase.create('easeSlowAtStart', 'M0,0,C0.48,0,0.573,0.075,0.72,0.288,0.894,0.54,0.95,0.752,1,1');

    rhymeAnim = new TimelineMax({paused:true})
        .add([
            TweenMax.from('#splashLetters .a', .5, {delay:0, 		y:-25,alpha:0,ease:Expo.easeOut}),
            TweenMax.from('#splashLetters .b', .5, {delay:.2, 		y: 25,alpha:0,ease:Expo.easeOut}),
            TweenMax.from('#splashLetters .terms', .5, {delay:.4, 	y:-25,alpha:0,ease:Expo.easeOut})
        ], '+=1.3')
        .add([
            TweenMax.to('#splashLetters .c1', .5, {y:25,autoAlpha:0,ease:Expo.easeInOut}),
            TweenMax.from('#splashLetters .c2', .5, {y:-25,autoAlpha:0,ease:Expo.easeInOut})
        ], '+=.2')
        .add([
            TweenMax.to('#splashLetters .c2', .5, {y:25,autoAlpha:0,ease:Expo.easeInOut}),
            TweenMax.from('#splashLetters .c3', .5, {y:-25,autoAlpha:0,ease:Expo.easeInOut})
        ], '+=.2')
        .add([
            TweenMax.to('#splashLetters .c3', .5, {y:25,autoAlpha:0,ease:Expo.easeInOut}),
            TweenMax.from('#splashLetters .c4', .5, {y:-25,autoAlpha:0,ease:Expo.easeInOut})
        ], '+=.2')
        .add([
            TweenMax.to('#splashLetters .c4', .5, {y:25,autoAlpha:0,ease:Expo.easeInOut}),
            TweenMax.from('#splashLetters .c5', .5, {y:-25,autoAlpha:0,ease:Expo.easeInOut}),
            TweenMax.from('#splashDot .dot', 1, {delay:.5, autoAlpha:0, y:-60, ease:Bounce.easeOut, onStart:setDotPosition})
        ], '+=.2')
        /*
            // Skip previous letters to demo
            .add([
                TweenMax.to('#splashLetters .c1', .6, {y:25,autoAlpha:0,ease:Expo.easeInOut}),
                TweenMax.from('#splashLetters .c5', .6, {y:-25,autoAlpha:0,ease:Expo.easeInOut}),
                TweenMax.from('#splashDot .dot', 1, {delay:.7, autoAlpha:0, y:-60, ease:Bounce.easeOut, onStart:setDotPosition})
            ], '+=.2')
        */
        // letter animation
        .to('#splashRhyme', 3, {scale:.8, ease:Quart.easeIn}, '-=.5')

        // dot animation
        .add([
            TweenMax.to('#splashDot', 1.3, { rotationZ:300, ease:'easeSlowAtStart' }),
            TweenMax.to('#splashDot', 1.3, { x:-1*Math.min(main.getWinW()*.7, 1000), y:0, ease:'easePauseAtStart_SlowAtEnd' }),
            TweenMax.fromTo(originObj, 1.3, { left:50, top:0}, {left:50, top:50, ease:'easeSlowAtStart', onUpdate:function(){
                    TweenMax.set('#splashDot', { transformOrigin:originObj.left + '% ' + originObj.top +  '%'});
                }}),
            TweenMax.to('#splashDot .dot', 1.3, { scale:Math.min(main.getWinW()*.6,380), ease:'easePauseAtStart_SlowAtEnd'}),
            TweenMax.to('#splashDot .dot .meteor', .4, {delay:.9, autoAlpha:1})
        ], '-=1.3')
        .addCallback(onRhymeDone);

    main.resizeWith(setDotPosition);

    function setDotPosition() {
        $('#splashDot .dot').css({
            left: $('#splashLetters .c5').offset().left + $('#splashLetters .c5').width(),
            top: $('#splashLetters .terms').offset().top + $('#splashLetters .terms').height() - 20
        });
    }

    endingAnim = new TimelineMax({paused:true})
        .add(taglineAnim)
        .add([
            TweenMax.to('.masthead, .mastfoot', 1.2, {autoAlpha:1, ease:Quad.easeOut}),
            TweenMax.fromTo('#navBar .dash', .6, {alpha:0, x:-50}, {alpha:1, x:0, ease:Quad.easeInOut})
        ], '-=1')
        .addCallback(function(){
            $('html').removeClass('intro-display');
            TweenMax.set('.masthead, .mastfoot', {zIndex:''});
        });


    $('#btnSkip').on('click', skipIntro);

    // -------------------------------------------

    signature = $('#splashSignature');

    signature.append('<img class="reel entrance" src="/static/reception/img/signature/entrance/137.png" width="1280">');
    signatureReel = $('img.entrance.reel', signature);
    signatureReel.add('<img src="">').imagesLoaded(initSignEntrance);


    signature.append('<img class="reel pebble" src="/static/reception/img/signature/pebble/89.png" width="77">');
    pebbleReel = $('img.pebble.reel', signature);
    pebbleReel.add('<img src="">').imagesLoaded(initSignPebble);


    function initSignEntrance() {
        signatureReel
            .on('loaded', function() {
                //log('initReel loaded');
                startIntro();
            })
            .on('frameChange', function(e, tier, frame){
                if(frame >= (137 - 5)) {
                    onSignatureAlmostDone();
                }
                if(frame >= 137) {
                    onSingatureDone();
                }
                log('frameChange =>', frame);
            })
            .reel({
                // http://test.vostrel.cz/jquery.reel/docs/jquery.reel.html
                images: '/static/reception/img/signature/entrance/#.png',
                frame: 1,
                frames: 137,
                klass: 'entrance',
                loops: false,
                rebound: 0,
                speed: 0, // stop on loaded
                laziness: 1, // for mobile engergy saving, the lower the more smoothing
                responsive: false // avoid resize event fires in reel.js
            });
    }
    function initSignPebble() {
        pebbleReel.reel({
            images: '/static/reception/img/signature/pebble/#.png',
            frame: 5, // start frame
            frames: 89,
            klass: 'pebble',
            loops: true,
            speed: 0,
            rebound: 0,
            laziness: 1,
            responsive: false
        });
    }
    function onRhymeDone() {
        $('#splashRhyme').hide();
        signature.css('visibility', 'visible');
        signatureReel.reel('speed', main.getWinW()>640?.5:.24).trigger('play');
        if(main.getWinH() > 1000) {
            TweenMax.from(signature, 1, { /*scale:1.2, x:-1*Math.min(main.getWinW()*.7,600),*/ y:main.getWinH()*-.3, clearProps:'transform', ease:Quad.easeOut});
        }

        TweenMax.to('#btnSkip', .3, {autoAlpha:0});
    }
    function startIntro() {
        if(started == true) return; started = true;
        log('signatureReel loaded startIntro()');
        window.introStarted = true;
        rhymeAnim.play(0);
        TweenMax.from('#btnSkip', 1, {delay:1, autoAlpha:0});
    }

    function onSignatureAlmostDone() {
        if(signAlmostDone) return; signAlmostDone = true;
        log('onSignatureAlmostDone()');
        pebbleReel.reel('speed', .3).trigger('play');
        TweenMax.from($('div.pebble', signature), 1, {autoAlpha:0, ease:Quart.easeOut});
    }
    function onSingatureDone() {
        if(signDone) return; signDone = true;
        log('onSingatureDone()');
        signatureReel.trigger('stop');//.trigger('reach', loopFrame).unreel().empty();
        endingAnim.play(0);
    }

    function skipIntro() {
        log('skipIntro()');

        rhymeAnim.pause();
        //setDotPosition();

        $('#splashRhyme').hide();
        $('#splashSignature').css('visibility', 'visible');
        TweenMax.from('#splashSignature .complete', 1, {autoAlpha:0, ease:Quad.easeInOut});

        TweenMax.killTweensOf(signature);
        TweenMax.set(signature,{y:0,clearProps:'transform'});

        onSingatureDone();
        signDone = true;
        signatureReel.parent().hide();

        pebbleReel.reel('speed', .3).trigger('play');
        TweenMax.from($('div.pebble', signature), 1, {autoAlpha:0, ease:Quart.easeOut});

        $('#btnSkip').hide();

        gatrack.event('intro-skip');
    }
};

$(main.home.initIntro);





// --------------------------------------------
// Works
// --------------------------------------------

main.works = {};
main.works.updateList = function() {
    if(main.device.mobile) {
        return; // do not apply to mobile
    }
    var list = $('#works .worklist');

    if(!!list.find('.owl-stage')[0]) { return; }

    log('updateList');

    list.addClass('owl-carousel');
    list.owlCarousel({
        items: 1,
        loop: false, nav: false, dots: true,
        fluidSpeed: 250,
        mouseDrag: false,
        //lazyLoad: true,
        responsiveRefreshRate: 11,
        onInitialized: function(){
            $('.owl-dots', list).appendTo(list);
            $('.owl-controls', list).hide();
        }
    });
}
$(function(){
    if(!$('#works')[0]) return;
    log('dafjldsajfas');
    $(document).on('beforeSectionChange ajaxListLoaded', function(){
        main.works.updateList();
    });
});

main.works.changeFilter = function() {
    main.sectionControl.ajaxList('works.html');
}


// --------------------------------------------

// Debug
$(function(){
    $('.newslist .title, .worklist .title').filter(':contains("[測")').css('background-color','blue');
});
// Unavailable nav links
$(function(){
    return;
    var sel = ''
        +  '[href*="#/about-history"], [href*="#/about-sr"], [href*="#/about-worldwide"], [href*="#/about-service"], [href*="#/about-belief"]'
        + ',[data-path*="#/about-history"], [data-path*="#/about-sr"], [data-path*="#/about-worldwide"], [data-path*="#/about-service"], [data-path*="#/about-belief"]'
        + ',[href*="works.html"]'
        + ',[href*="events.html"],[href*="media.html"]'
        + ',[href*="viewpoints.html"]'
        + ',#navBar [href="javascript:;"]'
    ;
    $(sel).attr('href', 'javascript:;')
        .css('opacity', .33).css('cursor', 'default');
});


$(function(){ !!/file/.test(location.protocol) && $('a[href="./"]').attr('href', 'index.aspx'); });

$(main.sectionControl.init);


$(document).ready(function () {
    var currentLocation = window.location;
    var currentLang = currentLocation.pathname.split("/")[1];
    var currentPage = currentLocation.pathname.split("/")[2].split(".")[0];

    if (currentPage == "media" || currentPage == "events")
        $('#news').addClass('on');
    else
        $('#' + currentPage).addClass('on');
});



