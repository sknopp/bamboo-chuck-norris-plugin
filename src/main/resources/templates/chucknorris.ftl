<html>
    <body>
        [#if resultSummary.isFailed()]
            <img src="${webResourceManager.getStaticResourcePrefix()}/download/resources/de.fraunhofer.fkie.caad.bamboo.chucknorris:chucknorris-resources/images/alert.jpg" />
        [#elseif $resultSummary.isSuccessful()]
            <img src="${webResourceManager.getStaticResourcePrefix()}/download/resources/de.fraunhofer.fkie.caad.bamboo.chucknorris:chucknorris-resources/images/thumb_up.jpg" />
        [#else]
            <img src="${webResourceManager.getStaticResourcePrefix()}/download/resources/de.fraunhofer.fkie.caad.bamboo.chucknorris:chucknorris-resources/images/bad_ass.jpg" />
        [/#if]
        <br />
        <b>${joke}</b>
        <br />
        <!--$action<br />
        $plan<br />
        $resultSummary<br />
        $resultSummary.getBuildTime() -->
    </body>
</html>