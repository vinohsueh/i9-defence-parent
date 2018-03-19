'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;        
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider',
      function ($stateProvider,   $urlRouterProvider) {
          
          $urlRouterProvider
              .otherwise('/app/dashboard-v1');
          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: 'tpl/app.html'
              })
              .state('app.project', {
                  url: '/project',
                  templateUrl: 'proj/project/project.html',
              	  controller : "projectNgControl",
              	  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'projectNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/project/project.js',
            				    ]
            			})
                    }]
                  }
              })  
              .state('app.client', {
                  url: '/client',
                  templateUrl: 'proj/client/client.html',
              	  controller : "clientControl",
              	  resolve: { 
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'clientModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/client/client.js',
            				    ]
            			})
                    }]
                  }
              }) 
            .state('app.apply', {
                  url: '/apply',
                  templateUrl: 'proj/apply/apply.html',
              	  controller : "applyNgControl",
              	  resolve: { 
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'applyNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/apply/apply.js',
            				    ]
            			})
                    }]
                  }
              }) 
              .state('app.manager', {
                  url: '/manager',
                  templateUrl: 'proj/manager/manager.html',
              	  controller : "managerNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'managerNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/manager/manager.js',
            				]
            			})
                    }]
                  }
              })
               .state('app.equipment', {
                  url: '/equipment',
                  templateUrl: 'proj/equipment/equipment.html',
              	  controller : "equipmentNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'equipmentNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/equipment/equipment.js',
            				]
            			})
                    }]
                  }
              })
               .state('app.streamOrigin', {
                  url: '/streamOrigin',
                  templateUrl: 'proj/streamOrigin/streamOrigin.html',
              	  controller : "streamOriginNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'streamOriginNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/streamOrigin/streamOrigin.js',
            				]
            			})
                    }]
                  }
              })
               .state('app.eqCategory', {
                  url: '/eqCategory',
                  templateUrl: 'proj/eqCategory/eqCategory.html',
              	  controller : "eqCategoryNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'eqCategoryNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/eqCategory/eqCategory.js',
            				]
            			})
                    }]
                  }
              })
              .state('app.pageUrl', {
                  url: '/pageUrl',
                  templateUrl: '/proj/page/page.html',
              	  controller : "pageUrlNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'pageUrlNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				         'proj/page/page.js',
            				         'css/child.css',
            				]
            			})
                    }]
                  }
              })
              .state('app.permission', {
                  url: '/permission',
                  templateUrl: 'proj/permission/permission.html',
              	  controller : "permissionNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'permissionNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/permission/permission.js',
            				]
            			})
                    }]
                  }
              })
              .state('app.role', {
                  url: '/role',
                  templateUrl: 'proj/role/role.html',
              	  controller : "roleNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'roleNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/role/role.js',
            				]
            			})
                    }]
                  }
              })
            .state('app.hiddendanger', {
			      url: '/hiddendanger',
			      templateUrl: 'proj/hiddendanger/hiddendanger.html',
			  	  controller : "hiddendangerNgControl",
			      resolve: {
			        deps: ['$ocLazyLoad',
			          function( $ocLazyLoad ){
			        	return $ocLazyLoad.load({
							name : 'hiddendangerNgModule',
							insertBefore : '#ng_load_plugins_before',
							files : [
							    'proj/hiddendanger/hiddendanger.js',
							]
	        			})
	                }]
	              }
	          })
	         
	                .state('app.hiddendangerInfo', {
			      url: '/hiddendangerInfo',
			      templateUrl: 'proj/hiddendangerInfo/hiddendangerInfo.html',
			  	  controller : "hiddendangerInfoNgControl",
			      resolve: {
			        deps: ['$ocLazyLoad',
			          function( $ocLazyLoad ){
			        	return $ocLazyLoad.load({
							name : 'hiddendangerInfoNgModule',
							insertBefore : '#ng_load_plugins_before',
							files : [
							    'proj/hiddendangerInfo/hiddendangerInfo.js',
							]
	        			})
	                }]
	              }
	          })
	         
	          
	          
	          
              .state('app.account', {
                  url: '/account',
                  templateUrl: 'proj/account/account.html',
              	  controller : "accountNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'accountNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/account/account.js',
            				]
            			})
                    }]
                  }
              })
              .state('app.applyaccount', {
                  url: '/applyaccount',
                  templateUrl: 'proj/applyaccount/applyaccount.html',
              	  controller : "applyAccountNgControl",
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                    	return $ocLazyLoad.load({
            				name : 'applyAccountNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				    'proj/applyaccount/applyaccount.js',
            				]
            			})
                    }]
                  }
              })
              .state('app.agency', {
                  url: '/agency',
                  templateUrl: 'proj/agency/agency.html',
                  controller : "agencyNgControl",
                  resolve: {
                      deps: ['$ocLazyLoad',
                          function( $ocLazyLoad ){
                              return $ocLazyLoad.load({
                                  name : 'agencyNgModule',
                                  insertBefore : '#ng_load_plugins_before',
                                  files : [
                                      'proj/agency/agency.js',
                                  ]
                              })
                          }]
                  }
              })
              .state('app.dashboard-v1', {
                  url: '/dashboard-v1',
                  templateUrl: '/tpl/app_dashboard_v1.html',
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                        return $ocLazyLoad.load(['js/controllers/chart.js']);
                    }]
                  }
              })
              .state('app.dashboard-v2', {
                  url: '/dashboard-v2',
                  templateUrl: '/tpl/app_dashboard_v2.html',
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                        return $ocLazyLoad.load(['js/controllers/chart.js']);
                    }]
                  }
              })
              .state('app.ui', {
                  url: '/ui',
                  template: '<div ui-view class="fade-in-up"></div>'
              })
              .state('app.ui.buttons', {
                  url: '/buttons',
                  templateUrl: 'tpl/ui_buttons.html'
              })
              .state('app.ui.icons', {
                  url: '/icons',
                  templateUrl: 'tpl/ui_icons.html'
              })
              .state('app.ui.grid', {
                  url: '/grid',
                  templateUrl: 'tpl/ui_grid.html'
              })
              .state('app.ui.widgets', {
                  url: '/widgets',
                  templateUrl: 'tpl/ui_widgets.html'
              })          
              .state('app.ui.bootstrap', {
                  url: '/bootstrap',
                  templateUrl: 'tpl/ui_bootstrap.html'
              })
              .state('app.ui.sortable', {
                  url: '/sortable',
                  templateUrl: 'tpl/ui_sortable.html'
              })
              .state('app.ui.portlet', {
                  url: '/portlet',
                  templateUrl: 'tpl/ui_portlet.html'
              })
              .state('app.ui.timeline', {
                  url: '/timeline',
                  templateUrl: 'tpl/ui_timeline.html'
              })
              .state('app.ui.tree', {
                  url: '/tree',
                  templateUrl: 'tpl/ui_tree.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('angularBootstrapNavTree').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/tree.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.ui.toaster', {
                  url: '/toaster',
                  templateUrl: 'tpl/ui_toaster.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('toaster').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/toaster.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.ui.jvectormap', {
                  url: '/jvectormap',
                  templateUrl: 'tpl/ui_jvectormap.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('js/controllers/vectormap.js');
                      }]
                  }
              })
              .state('app.ui.googlemap', {
                  url: '/googlemap',
                  templateUrl: 'tpl/ui_googlemap.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( [
                            'js/app/map/load-google-maps.js',
                            'js/app/map/ui-map.js',
                            'js/app/map/map.js'] ).then(
                              function(){
                                return loadGoogleMaps(); 
                              }
                            );
                      }]
                  }
              })
              .state('app.chart', {
                  url: '/chart',
                  templateUrl: 'tpl/ui_chart.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad){
                          return uiLoad.load('js/controllers/chart.js');
                      }]
                  }
              })
              // table
              .state('app.table', {
                  url: '/table',
                  template: '<div ui-view></div>'
              })
              .state('app.table.static', {
                  url: '/static',
                  templateUrl: 'tpl/table_static.html'
              })
              .state('app.table.datatable', {
                  url: '/datatable',
                  templateUrl: 'tpl/table_datatable.html'
              })
              .state('app.table.footable', {
                  url: '/footable',
                  templateUrl: 'tpl/table_footable.html'
              })
              .state('app.table.grid', {
                  url: '/grid',
                  templateUrl: 'tpl/table_grid.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/grid.js');
                              }
                          );
                      }]
                  }
              })
              // form
              .state('app.form', {
                  url: '/form',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad){
                          return uiLoad.load('js/controllers/form.js');
                      }]
                  }
              })
              .state('app.form.elements', {
                  url: '/elements',
                  templateUrl: 'tpl/form_elements.html'
              })
              .state('app.form.validation', {
                  url: '/validation',
                  templateUrl: 'tpl/form_validation.html'
              })
              .state('app.form.wizard', {
                  url: '/wizard',
                  templateUrl: 'tpl/form_wizard.html'
              })
              .state('app.form.fileupload', {
                  url: '/fileupload',
                  templateUrl: 'tpl/form_fileupload.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('angularFileUpload').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/file-upload.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.imagecrop', {
                  url: '/imagecrop',
                  templateUrl: 'tpl/form_imagecrop.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('ngImgCrop').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/imgcrop.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.select', {
                  url: '/select',
                  templateUrl: 'tpl/form_select.html',
                  controller: 'SelectCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ui.select').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/select.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.slider', {
                  url: '/slider',
                  templateUrl: 'tpl/form_slider.html',
                  controller: 'SliderCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('vr.directives.slider').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/slider.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.editor', {
                  url: '/editor',
                  templateUrl: 'tpl/form_editor.html',
                  controller: 'EditorCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('textAngular').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/editor.js');
                              }
                          );
                      }]
                  }
              })
              // pages
              .state('app.page', {
                  url: '/page',
                  template: '<div ui-view class="fade-in-down"></div>'
              })
              .state('app.page.profile', {
                  url: '/profile',
                  templateUrl: 'tpl/page_profile.html'
              })
              .state('app.page.post', {
                  url: '/post',
                  templateUrl: 'tpl/page_post.html'
              })
              .state('app.page.search', {
                  url: '/search',
                  templateUrl: 'tpl/page_search.html'
              })
              .state('app.page.invoice', {
                  url: '/invoice',
                  templateUrl: 'tpl/page_invoice.html'
              })
              .state('app.page.price', {
                  url: '/price',
                  templateUrl: 'tpl/page_price.html'
              })
              .state('app.docs', {
                  url: '/docs',
                  templateUrl: 'tpl/docs.html'
              })
              // others
              .state('lockme', {
                  url: '/lockme',
                  templateUrl: 'tpl/page_lockme.html'
              })
              .state('access', {
                  url: '/access',
                  template: '<div ui-view class="fade-in-right-big smooth"></div>'
              })
              .state('access.signin', {
                  url: '/signin',
                  templateUrl: 'tpl/page_signin.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/signin.js'] );
                      }]
                  }
              })
              .state('access.signup', {
                  url: '/signup',
                  templateUrl: 'tpl/page_signup.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/signup.js'] );
                      }]
                  }
              })
              .state('access.forgotpwd', {
                  url: '/forgotpwd',
                  templateUrl: 'tpl/page_forgotpwd.html'
              })
              .state('access.404', {
                  url: '/404',
                  templateUrl: 'tpl/page_404.html'
              })

              // fullCalendar
              .state('app.calendar', {
                  url: '/calendar',
                  templateUrl: 'tpl/app_calendar.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['$ocLazyLoad', 'uiLoad',
                        function( $ocLazyLoad, uiLoad ){
                          return uiLoad.load(
                            ['vendor/jquery/fullcalendar/fullcalendar.css',
                              'vendor/jquery/fullcalendar/theme.css',
                              'vendor/jquery/jquery-ui-1.10.3.custom.min.js',
                              'vendor/libs/moment.min.js',
                              'vendor/jquery/fullcalendar/fullcalendar.min.js',
                              'js/app/calendar/calendar.js']
                          ).then(
                            function(){
                              return $ocLazyLoad.load('ui.calendar');
                            }
                          )
                      }]
                  }
              })

              // mail
              .state('app.mail', {
                  abstract: true,
                  url: '/mail',
                  templateUrl: 'tpl/mail.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/mail/mail.js',
                                               'js/app/mail/mail-service.js',
                                               'vendor/libs/moment.min.js'] );
                      }]
                  }
              })
              .state('app.mail.list', {
                  url: '/inbox/{fold}',
                  templateUrl: 'tpl/mail.list.html'
              })
              .state('app.mail.detail', {
                  url: '/{mailId:[0-9]{1,4}}',
                  templateUrl: 'tpl/mail.detail.html'
              })
              .state('app.mail.compose', {
                  url: '/compose',
                  templateUrl: 'tpl/mail.new.html'
              })

              .state('layout', {
                  abstract: true,
                  url: '/layout',
                  templateUrl: 'tpl/layout.html'
              })
              .state('layout.fullwidth', {
                  url: '/fullwidth',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_fullwidth.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/vectormap.js'] );
                      }]
                  }
              })
              .state('layout.mobile', {
                  url: '/mobile',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_mobile.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_mobile.html'
                      }
                  }
              })
              .state('layout.app', {
                  url: '/app',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_app.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/tab.js'] );
                      }]
                  }
              })
              .state('apps', {
                  abstract: true,
                  url: '/apps',
                  templateUrl: 'tpl/layout.html'
              })
              .state('apps.note', {
                  url: '/note',
                  templateUrl: 'tpl/apps_note.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/note/note.js',
                                               'vendor/libs/moment.min.js'] );
                      }]
                  }
              })
              .state('apps.contact', {
                  url: '/contact',
                  templateUrl: 'tpl/apps_contact.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/contact/contact.js'] );
                      }]
                  }
              })
              .state('app.weather', {
                  url: '/weather',
                  templateUrl: 'tpl/apps_weather.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(
                              {
                                  name: 'angular-skycons',
                                  files: ['js/app/weather/skycons.js',
                                          'vendor/libs/moment.min.js', 
                                          'js/app/weather/angular-skycons.js',
                                          'js/app/weather/ctrl.js' ] 
                              }
                          );
                      }]
                  }
              })
              .state('music', {
                  url: '/music',
                  templateUrl: 'tpl/music.html',
                  controller: 'MusicCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load([
                            'com.2fdevs.videogular', 
                            'com.2fdevs.videogular.plugins.controls', 
                            'com.2fdevs.videogular.plugins.overlayplay',
                            'com.2fdevs.videogular.plugins.poster',
                            'com.2fdevs.videogular.plugins.buffering',
                            'js/app/music/ctrl.js', 
                            'js/app/music/theme.css'
                          ]);
                      }]
                  }
              })
                .state('music.home', {
                    url: '/home',
                    templateUrl: 'tpl/music.home.html'
                })
                .state('music.genres', {
                    url: '/genres',
                    templateUrl: 'tpl/music.genres.html'
                })
                .state('music.detail', {
                    url: '/detail',
                    templateUrl: 'tpl/music.detail.html'
                })
                .state('music.mtv', {
                    url: '/mtv',
                    templateUrl: 'tpl/music.mtv.html'
                })
                .state('music.mtvdetail', {
                    url: '/mtvdetail',
                    templateUrl: 'tpl/music.mtv.detail.html'
                })
                .state('music.playlist', {
                    url: '/playlist/{fold}',
                    templateUrl: 'tpl/music.playlist.html'
                })
      }
    ]
  );
