<?php

$galeria = '
                    <!-- Portfolio Grid-->
            <section class="page-section bg-light" id="portfolio">
                <div class="container">
                    <div class="text-center">
                        <h2 class="section-heading text-uppercase">Galeria</h2>
                        <h3 class="section-subheading text-muted">bo człowiek je oczami...</h3>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 col-sm-6 mb-4">
                            <!-- Portfolio item 1-->
                            <div class="portfolio-item">
                                <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1" id="modal1" >
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-search fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="assets/img/portfolio/sandwitch/1.jpg" alt="..." />
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">Kanapki</div>
                                    <div class="portfolio-caption-subheading text-muted">Dla szkół i firm</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 mb-4">
                            <!-- Portfolio item 2-->
                            <div class="portfolio-item">
                                <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal2" id="modal2">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-search fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="assets/img/portfolio/full/1.jpg" alt="..." />
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">Pełny catering</div>
                                    <div class="portfolio-caption-subheading text-muted">Zaopatrzenie imprez</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 mb-4">
                            <!-- Portfolio item 3-->
                            <div class="portfolio-item">
                                <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal3" id="modal3">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-search fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="assets/img/portfolio/soup/1.jpg" alt="..." />
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">Zupy</div>
                                    <div class="portfolio-caption-subheading text-muted"> &nbsp;</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
                            <!-- Portfolio item 4-->
                            <div class="portfolio-item">
                                <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal4" id="modal4">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-search fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="assets/img/portfolio/dish/1.jpg" alt="..." />
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">Obiady</div>
                                    <div class="portfolio-caption-subheading text-muted">Dania główne i/lub zupy</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
                            <!-- Portfolio item 5-->
                            <div class="portfolio-item">
                                <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal5" id="modal5">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-search fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="assets/img/portfolio/dessert/1.jpg" alt="..." />
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">Desery</div>
                                    <div class="portfolio-caption-subheading text-muted">&nbsp;</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <!-- Portfolio item 6-->
                            <div class="portfolio-item">
                                <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal6" id="modal6">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-search fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="assets/img/portfolio/salad/1.jpg" alt="..." />
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">Sałatki</div>
                                    <div class="portfolio-caption-subheading text-muted">Przygotowane przez nas lub własne</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>';
            $tytuly =["Kanapki","Pełny catering","Zupy","Obiady","Desery","Sałatki"];
            for($i=1;$i<=6;$i++){
                $galeria .= '<div class="portfolio-modal modal fade" id="portfolioModal'.$i.'" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-8">
                                    <div class="modal-body">
                                        <!-- Project details-->
                                        <h2 class="text-uppercase">'.$tytuly[$i-1].'</h2>
                                        <!-- Slideshow container -->

                                        <div class="slideshow-container" id="modal'.$i.'x"></div>

                                        <br>

                                        <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                            <i class="fas fa-times me-1"></i>
                                            Zamknij
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>';
            }