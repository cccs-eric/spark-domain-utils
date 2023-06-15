package cccs.hogwarts.domainutils;

import com.google.common.net.InternetDomainName;
import org.apache.spark.sql.api.java.UDF1;

public class IsSubdomainUDF implements UDF1<String, Boolean> {

    @Override
    public Boolean call(String domain) throws Exception {
        try {
            InternetDomainName idn = InternetDomainName.from(domain);
            // System.out.println("domain = "+domain);
            // System.out.println(" isUnderPublicSuffix? "+idn.isUnderPublicSuffix());
            // System.out.println(" publicSuffix = "+idn.publicSuffix().toString());
            // System.out.println(" isUnderRegistrySuffix? "+idn.isUnderRegistrySuffix());
            // System.out.println(" registrySuffix = "+idn.registrySuffix().toString());
            // System.out.println(" isTopPrivateDomain? "+idn.isTopPrivateDomain());
            // System.out.println(" topPrivateDomain = "+(idn.isTopPrivateDomain() ?
            // idn.topPrivateDomain().toString(): "N/A"));
            // System.out.println(" isTopDomainUnderRegistrySuffix?
            // "+idn.isTopDomainUnderRegistrySuffix());
            // System.out.println(" topDomainUnderRegistrySuffix = "+
            // (idn.isTopDomainUnderRegistrySuffix()?idn.topDomainUnderRegistrySuffix().toString():
            // "N/A"));
            // System.out.println(" parts = "+idn.parts());
            // System.out.println();
            String topDomain = domain;
            if (idn.isUnderPublicSuffix())
                topDomain = idn.topDomainUnderRegistrySuffix().toString();
            else if (idn.isTopPrivateDomain())
                topDomain = idn.topPrivateDomain().toString();
            return !domain.equalsIgnoreCase(topDomain);
        } catch (IllegalArgumentException ex) {
            // If input was not a domain, consider it is not a subdomain and return false
            return false;
        }
    }
}