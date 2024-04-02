package com.turgaydede.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
            new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(
                jwt,
                authorities,
                getPrincipleClaimName(jwt)
        );
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null) {
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }
private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

    Map<String, Object> realmAccess = jwt.getClaim("realm_access");
    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

    Collection<String> allRoles = new ArrayList<>();
    Collection<String> resourceRoles;
    Collection<String> realmRoles ;

    if(resourceAccess != null && resourceAccess.get(resourceId) != null){
        Map<String,Object> resource =  (Map<String,Object>) resourceAccess.get(resourceId);
        if(resource.containsKey("roles") ){
            resourceRoles = (Collection<String>) resource.get("roles");
            allRoles.addAll(resourceRoles);
        }
    }

    if(realmAccess != null && realmAccess.containsKey("roles")){
        realmRoles = (Collection<String>) realmAccess.get("roles");
        allRoles.addAll(realmRoles);
    }
    if (allRoles.isEmpty() || !Objects.equals(resourceId,jwt.getClaim("azp")) ) {

        return Set.of();
    }

    return allRoles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toSet());
}
}
