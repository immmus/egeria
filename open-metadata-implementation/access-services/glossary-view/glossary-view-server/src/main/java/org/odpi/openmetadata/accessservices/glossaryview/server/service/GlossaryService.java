/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.glossaryview.server.service;

import org.odpi.openmetadata.accessservices.glossaryview.rest.GlossaryViewEntityDetailResponse;

/**
 * Server-side implementation of the Glossary View Open Metadata Access Service (OMAS).
 * Deals in extracting glossaries
 */
public class GlossaryService extends GlossaryViewOMAS {

    private static final String GLOSSARY_TYPE_NAME = "Glossary";
    private static final String GLOSSARY_TYPE_GUID = "36f66863-9726-4b41-97ee-714fd0dc6fe4";

    private static final String EXTERNALLY_SOURCED_GLOSSARY_RELATIONSHIP_NAME = "ExternallySourcedGlossary";
    private static final String EXTERNALLY_SOURCED_GLOSSARY_RELATIONSHIP_GUID = "7786a39c-436b-4538-acc7-d595b5856add";

    private static final String TERM_TYPE_NAME = "GlossaryTerm";

    private static final String TERM_ANCHOR_RELATIONSHIP_NAME = "TermAnchor";
    private static final String TERM_ANCHOR_RELATIONSHIP_GUID = "1d43d661-bdc7-4a91-a996-3239b8f82e56";

    private static final String CATEGORY_TYPE_NAME = "GlossaryCategory";

    private static final String CATEGORY_ANCHOR_RELATIONSHIP_NAME = "CategoryAnchor";
    private static final String CATEGORY_ANCHOR_RELATIONSHIP_GUID = "c628938e-815e-47db-8d1c-59bb2e84e028";

    public GlossaryService() {}

    /**
     * Extract the glossary definition for the given GUID
     *
     * @param userId calling user
     * @param serverName instance to call
     * @param glossaryGUID glossary GUID
     *
     * @return EntityDetailResponse glossary
     */
    public GlossaryViewEntityDetailResponse getGlossary(String userId, String serverName, String glossaryGUID){
        return getEntityDetailResponse(userId, serverName, glossaryGUID, GLOSSARY_TYPE_NAME, "getGlossary");
    }

    /**
     * Extract all glossary definitions
     *
     * @param userId calling user
     * @param serverName instance to call
     * @param from from
     * @param size size
     *
     * @return EntityDetailResponse all glossaries
     */
    public GlossaryViewEntityDetailResponse getAllGlossaries(String userId, String serverName, Integer from, Integer size){
        return getAllEntityDetailsResponse(userId, serverName, GLOSSARY_TYPE_GUID, from, size, "getAllGlossaries");
    }

    /**
     * Extract a term's home glossary
     *
     * @param userId calling user
     * @param serverName instance to call
     * @param termGUID term GUID
     *
     * @return glossary
     */
    public GlossaryViewEntityDetailResponse getTermHomeGlossary(String userId, String serverName, String termGUID){
        return getRelatedEntitiesResponse(userId, serverName, termGUID, TERM_TYPE_NAME,
                TERM_ANCHOR_RELATIONSHIP_GUID, TERM_ANCHOR_RELATIONSHIP_NAME,
                0, 0, "getTermHomeGlossary");
    }

    /**
     * Extract a category's home glossary
     *
     * @param userId calling user
     * @param serverName instance to call
     * @param categoryGUID term GUID
     *
     * @return glossary
     */
    public GlossaryViewEntityDetailResponse getCategoryHomeGlossary(String userId, String serverName, String categoryGUID){
        return getRelatedEntitiesResponse(userId, serverName, categoryGUID, CATEGORY_TYPE_NAME,
                CATEGORY_ANCHOR_RELATIONSHIP_GUID, CATEGORY_ANCHOR_RELATIONSHIP_NAME,
                0, 0, "getCategoryHomeGlossary");
    }

    /**
     * Extract external glossary definitions
     *
     * @param userId calling user
     * @param serverName instance to call
     * @param glossaryGUID glossary GUID
     * @param from from
     * @param size size
     *
     * @return EntityDetailResponse all external glossaries
     */
    public GlossaryViewEntityDetailResponse getExternalGlossaries(String userId, String serverName, String glossaryGUID,
                                                                  Integer from, Integer size){
        return getRelatedEntitiesResponse(userId, serverName, glossaryGUID, GLOSSARY_TYPE_NAME,
                EXTERNALLY_SOURCED_GLOSSARY_RELATIONSHIP_GUID, EXTERNALLY_SOURCED_GLOSSARY_RELATIONSHIP_NAME,
                from, size, "getExternalGlossaries");
    }

}