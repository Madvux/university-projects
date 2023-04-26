/*!
 * Start Bootstrap - Agency v7.0.0 (https://startbootstrap.com/theme/agency)
 * Copyright 2013-2021 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
 */

window.addEventListener('DOMContentLoaded', event => {

// Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink');
        } else {
            navbarCollapsible.classList.add('navbar-shrink');
        }

    };
    // Shrink the navbar 
    navbarShrink();
    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);
    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74
        });
    }
    ;
    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
            document.querySelectorAll('#navbarResponsive .nav-link')
            );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });
//##########################################################################
    var modal1 = document.getElementById("modal1");
    modal1.addEventListener('click', function () {
        fetch("http://localhost/assets/modals/modal1.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal1x").innerHTML = html;
                });
    }, false);
    
    var modal2 = document.getElementById("modal2");
    modal2.addEventListener('click', function () {
        fetch("http://localhost/assets/modals/modal2.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal2x").innerHTML = html;
                });
    }, false);
    
    var modal3 = document.getElementById("modal3");
    modal3.addEventListener('click', function () {
        fetch("http://localhost/assets/modals/modal3.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal3x").innerHTML = html;
                });
    }, false);
    
    var modal4 = document.getElementById("modal4");
    modal4.addEventListener('click', function () {
        fetch("http://localhost/assets/modals/modal4.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal4x").innerHTML = html;
                });
    }, false);
    
    var modal5 = document.getElementById("modal5");
    modal5.addEventListener('click', function () {
        fetch("http://localhost/assets/modals/modal5.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal5x").innerHTML = html;
                });
    }, false);
    
    var modal6 = document.getElementById("modal6");
    modal6.addEventListener('click', function () {
        fetch("http://localhost/assets/modals/modal6.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal6x").innerHTML = html;
                });
    }, false);
    
    });