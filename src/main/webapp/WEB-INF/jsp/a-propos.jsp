<%@ include file="include/importTags.jsp" %>

<div class="container my-5">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="display-4 mb-4"><spring:message code="about.title"/></h1>
            
            <!-- Mission Section -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h2 class="card-title h3 text-primary"><spring:message code="about.mission.title"/></h2>
                    <p class="card-text lead"><spring:message code="about.mission.text"/></p>
                </div>
            </div>
            
            <!-- Values Section -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h2 class="card-title h3 text-primary"><spring:message code="about.values.title"/></h2>
                    <ul class="list-unstyled">
                        <li class="mb-3">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            <strong><spring:message code="about.values.quality"/></strong>
                        </li>
                        <li class="mb-3">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            <strong><spring:message code="about.values.innovation"/></strong>
                        </li>
                        <li class="mb-3">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            <strong><spring:message code="about.values.service"/></strong>
                        </li>
                    </ul>
                </div>
            </div>
            
            <!-- History Section -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h2 class="card-title h3 text-primary"><spring:message code="about.history.title"/></h2>
                    <p class="card-text"><spring:message code="about.history.text"/></p>
                </div>
            </div>
            
            <!-- Commitment Section -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h2 class="card-title h3 text-primary"><spring:message code="about.commitment.title"/></h2>
                    <p class="card-text"><spring:message code="about.commitment.text"/></p>
                </div>
            </div>
            
            <!-- Call to Action -->
            <div class="text-center mt-5">
                <a href="<spring:url value='/produits'/>" class="btn btn-primary btn-lg">
                    <spring:message code="catalog.btn.view"/>
                </a>
            </div>
        </div>
    </div>
</div>
