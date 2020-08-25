/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.subjectarea.server.services;

import org.odpi.openmetadata.accessservices.subjectarea.handlers.SubjectAreaGlossaryHandler;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.category.Category;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.common.FindRequest;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.glossary.Glossary;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.Line;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.term.Term;
import org.odpi.openmetadata.accessservices.subjectarea.responses.SubjectAreaOMASAPIResponse;
import org.odpi.openmetadata.frameworks.connectors.ffdc.OCFCheckedExceptionBase;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.SequencingOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * The SubjectAreaRESTServicesInstance provides the org.odpi.openmetadata.accessservices.subjectarea.server-side implementation of the SubjectArea Open Metadata
 * Access Service (OMAS).  This interface provides glossary authoring interfaces for subject area experts.
 */

public class SubjectAreaGlossaryRESTServices extends SubjectAreaRESTServicesInstance {
    private static final Logger log = LoggerFactory.getLogger(SubjectAreaGlossaryRESTServices.class);
    private static final SubjectAreaInstanceHandler instanceHandler = new SubjectAreaInstanceHandler();
    private static final String className = SubjectAreaGlossaryRESTServices.class.getName();

    /**
     * Default constructor
     */
    public SubjectAreaGlossaryRESTServices() {
    }

    /**
     * Create a Glossary. There are specializations of glossaries that can also be created using this operation.
     * To create a specialization, you should specify a nodeType other than Glossary in the supplied glossary.
     * <p>
     * Glossaries with the same name can be confusing. Best practise is to createGlossaries that have unique names.
     * This Create call does not police that glossary names are unique. So it is possible to create Glossaries with the same name as each other.
     *
     * <p>
     * Valid nodeTypes for this request are:
     * <ul>
     * <li>Taxonomy to create a Taxonomy </li>
     * <li>CanonicalGlossary to create a canonical glossary </li>
     * <li>TaxonomyAndCanonicalGlossary to create a glossary that is both a taxonomy and a canonical glossary </li>
     * <li>Glossary to create a glossary that is not a taxonomy or a canonical glossary</li>
     * </ul>
     *
     * @param serverName       serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId           unique identifier for requesting user, under which the request is performed
     * @param suppliedGlossary Glossary to create
     * @return response, when successful contains the created glossary.
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Glossary> createGlossary(String serverName, String userId, Glossary suppliedGlossary) {
        final String methodName = "createGlossary";
        SubjectAreaOMASAPIResponse<Glossary> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.createGlossary(userId, suppliedGlossary);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        return response;
    }

    /**
     * Get a glossary by guid.
     *
     * @param serverName serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId     unique identifier for requesting user, under which the request is performed
     * @param guid       guid of the glossary to get
     * @return response which when successful contains the glossary with the requested guid
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Glossary> getGlossaryByGuid(String serverName, String userId, String guid) {
        final String methodName = "getGlossaryByGuid";
        SubjectAreaOMASAPIResponse<Glossary> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.getGlossaryByGuid(userId, guid);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        return response;
    }

    /**
     * Find Glossary
     *
     * @param serverName         serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId             unique identifier for requesting user, under which the request is performed
     * @param searchCriteria     String expression matching Glossary property values. If not specified then all glossaries are returned.
     * @param asOfTime           the glossaries returned as they were at this time. null indicates at the current time.
     * @param offset             the starting element number for this set of results.  This is used when retrieving elements
     *                           beyond the first page of results. Zero means the results start from the first element.
     * @param pageSize           the maximum number of elements that can be returned on this request.
     *                           0 means there is no limit to the page size
     * @param sequencingOrder    the sequencing order for the results.
     * @param sequencingProperty the name of the property that should be used to sequence the results.
     * @return A list of Glossaries meeting the search Criteria
     *
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Glossary> findGlossary(String serverName,
                                                             String userId,
                                                             String searchCriteria,
                                                             Date asOfTime,
                                                             Integer offset,
                                                             Integer pageSize,
                                                             SequencingOrder sequencingOrder,
                                                             String sequencingProperty) {

        final String methodName = "findGlossary";
        SubjectAreaOMASAPIResponse<Glossary> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            FindRequest findRequest = new FindRequest();
            findRequest.setSearchCriteria(searchCriteria);
            findRequest.setAsOfTime(asOfTime);
            findRequest.setOffset(offset);
            findRequest.setPageSize(pageSize);
            findRequest.setSequencingOrder(sequencingOrder);
            findRequest.setSequencingProperty(sequencingProperty);
            response = handler.findGlossary(userId, findRequest);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }

        return response;
    }

    /**
     * Get Glossary relationships
     *
     * @param serverName serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId unique identifier for requesting user, under which the request is performed
     * @param guid   guid of the term to get
     * @param asOfTime the relationships returned as they were at this time. null indicates at the current time. If specified, the date is in milliseconds since 1970-01-01 00:00:00.
     * @param offset  the starting element number for this set of results.  This is used when retrieving elements
     *                 beyond the first page of results. Zero means the results start from the first element.
     * @param pageSize the maximum number of elements that can be returned on this request.
     *                 0 means there is not limit to the page size
     * @param sequencingOrder the sequencing order for the results.
     * @param sequencingProperty the name of the property that should be used to sequence the results.
     * @return the relationships associated with the requested Glossary guid
     *
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Line> getGlossaryRelationships(String serverName,
                                                                     String userId,
                                                                     String guid,
                                                                     Date asOfTime,
                                                                     Integer offset,
                                                                     Integer pageSize,
                                                                     SequencingOrder sequencingOrder,
                                                                     String sequencingProperty) {
        String methodName = "getGlossaryRelationships";
        if (log.isDebugEnabled()) {
            log.debug("==> Method: " + methodName + ",userId=" + userId + ",guid=" + guid);
        }
        SubjectAreaOMASAPIResponse<Line> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            FindRequest findRequest = new FindRequest();
            findRequest.setAsOfTime(asOfTime);
            findRequest.setOffset(offset);
            findRequest.setPageSize(pageSize);
            findRequest.setSequencingOrder(sequencingOrder);
            findRequest.setSequencingProperty(sequencingProperty);
            response = handler.getGlossaryRelationships(userId, guid, findRequest);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        if (log.isDebugEnabled()) {
            log.debug("<== successful method : " + methodName + ",userId=" + userId + ", response =" + response);
        }

        return response;
    }

    /**
     * Update a Glossary
     * <p>
     * If the caller has chosen to incorporate the glossary name in their Glossary Terms or Categories qualified name, renaming the glossary will cause those
     * qualified names to mismatch the Glossary name.
     * If the caller has chosen to incorporate the glossary qualifiedName in their Glossary Terms or Categories qualified name, changing the qualified name of the glossary will cause those
     * qualified names to mismatch the Glossary name.
     * Status is not updated using this call.
     *
     * @param serverName       serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId           unique identifier for requesting user, under which the request is performed
     * @param guid             guid of the glossary to update
     * @param suppliedGlossary glossary to be updated
     * @param isReplace        flag to indicate that this update is a replace. When not set only the supplied (non null) fields are updated.
     * @return a response which when successful contains the updated glossary
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Glossary> updateGlossary(String serverName, String userId, String guid, Glossary suppliedGlossary, boolean isReplace) {
        final String methodName = "updateGlossary";
        if (log.isDebugEnabled()) {
            log.debug("==> Method: " + methodName + ",userId=" + userId + ",guid=" + guid);
        }
        SubjectAreaOMASAPIResponse<Glossary> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.updateGlossary(userId, guid, suppliedGlossary, isReplace);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        if (log.isDebugEnabled()) {
            log.debug("<== successful method : " + methodName + ",userId=" + userId + ", response =" + response);
        }
        return response;
    }


    /**
     * Delete a Glossary instance
     * <p>
     * The deletion of a glossary is only allowed if there is no glossary content (i.e. no terms or categories).
     * <p>
     * There are 2 types of deletion, a soft delete and a hard delete (also known as a purge). All repositories support hard deletes. Soft deletes support
     * is optional. Soft delete is the default.
     * <p>
     * A soft delete means that the glossary instance will exist in a deleted state in the repository after the delete operation. This means
     * that it is possible to undo the delete.
     * A hard delete means that the glossary will not exist after the operation.
     * when not successful the following Exceptions can occur
     *
     * @param serverName serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId     unique identifier for requesting user, under which the request is performed
     * @param guid       guid of the glossary to be deleted.
     * @param isPurge    true indicates a hard delete, false is a soft delete.
     * @return a void response
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * <li> EntityNotDeletedException            a soft delete was issued but the glossary was not deleted.</li>
     * <li> EntityNotPurgedException             a hard delete was issued but the glossary was not purged</li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Glossary> deleteGlossary(String serverName, String userId, String guid, Boolean isPurge) {
        final String methodName = "deleteGlossary";
        if (log.isDebugEnabled()) {
            log.debug("==> Method: " + methodName + ",userId=" + userId + ",guid=" + guid);
        }
        SubjectAreaOMASAPIResponse<Glossary> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.deleteGlossary(userId, guid, isPurge);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        if (log.isDebugEnabled()) {
            log.debug("<== successful method : " + methodName + ",userId=" + userId + ", response =" + response);
        }
        return response;
    }


    /**
     * Restore a Glossary
     * <p>
     * Restore allows the deleted Glossary to be made active again. Restore allows deletes to be undone. Hard deletes are not stored in the repository so cannot be restored.
     *
     * @param serverName serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId     unique identifier for requesting user, under which the request is performed
     * @param guid       guid of the glossary to restore
     * @return response which when successful contains the restored glossary
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     */
    public SubjectAreaOMASAPIResponse<Glossary> restoreGlossary(String serverName, String userId, String guid) {
        final String methodName = "restoreGlossary";
        if (log.isDebugEnabled()) {
            log.debug("==> Method: " + methodName + ",userId=" + userId + ",guid=" + guid);
        }
        SubjectAreaOMASAPIResponse<Glossary> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.restoreGlossary(userId, guid);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        if (log.isDebugEnabled()) {
            log.debug("<== successful method : " + methodName + ",userId=" + userId + ", response =" + response);
        }
        return response;
    }

    /**
     * Get Glossary terms
     *
     * @param serverName serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId     unique identifier for requesting user, under which the request is performed
     * @param guid       guid of the category to get terms
     * @return A list of terms for the glossary
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     * */
    public SubjectAreaOMASAPIResponse<Term> getGlossaryTerms(String serverName, String userId, String guid) {
        final String methodName = "getGlossaryTerms";
        if (log.isDebugEnabled()) {
            log.debug("==> Method: " + methodName + ",userId=" + userId + ",guid=" + guid);
        }
        SubjectAreaOMASAPIResponse<Term> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.getGlossaryTerms(userId, guid);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        if (log.isDebugEnabled()) {
            log.debug("<== successful method : " + methodName + ",userId=" + userId + ", response =" + response);
        }
        return response;
    }

    /**
     * Get Glossary categories
     *
     * @param serverName serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId     unique identifier for requesting user, under which the request is performed
     * @param guid       guid of the category to get terms
     * @return A list of categories for the glossary
     * when not successful the following Exception responses can occur
     * <ul>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> PropertyServerException              Property server exception. </li>
     * </ul>
     * */
    public SubjectAreaOMASAPIResponse<Category> getGlossaryCategories(String serverName, String userId, String guid) {
        final String methodName = "getGlossaryCategories";
        if (log.isDebugEnabled()) {
            log.debug("==> Method: " + methodName + ",userId=" + userId + ",guid=" + guid);
        }
        SubjectAreaOMASAPIResponse<Category> response = new SubjectAreaOMASAPIResponse<>();
        try {
            SubjectAreaGlossaryHandler handler = instanceHandler.getSubjectAreaGlossaryHandler(userId, serverName, methodName);
            response = handler.getGlossaryCategories(userId, guid);
        } catch (OCFCheckedExceptionBase e) {
            response.setExceptionInfo(e, className);
        }
        if (log.isDebugEnabled()) {
            log.debug("<== successful method : " + methodName + ",userId=" + userId + ", response =" + response);
        }
        return response;
    }
}