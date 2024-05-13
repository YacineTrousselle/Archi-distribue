//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `Song.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package fr.frcsbcn.soup.generated;

public interface AudioPlayerPrx extends com.zeroc.Ice.ObjectPrx
{
    default String initializeAudioPlayer(String songId)
    {
        return initializeAudioPlayer(songId, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String initializeAudioPlayer(String songId, java.util.Map<String, String> context)
    {
        return _iceI_initializeAudioPlayerAsync(songId, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<String> initializeAudioPlayerAsync(String songId)
    {
        return _iceI_initializeAudioPlayerAsync(songId, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<String> initializeAudioPlayerAsync(String songId, java.util.Map<String, String> context)
    {
        return _iceI_initializeAudioPlayerAsync(songId, context, false);
    }

    /**
     * @hidden
     * @param iceP_songId -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<String> _iceI_initializeAudioPlayerAsync(String iceP_songId, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "initializeAudioPlayer", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_songId);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default void play(String rtspUrl)
    {
        play(rtspUrl, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void play(String rtspUrl, java.util.Map<String, String> context)
    {
        _iceI_playAsync(rtspUrl, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> playAsync(String rtspUrl)
    {
        return _iceI_playAsync(rtspUrl, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> playAsync(String rtspUrl, java.util.Map<String, String> context)
    {
        return _iceI_playAsync(rtspUrl, context, false);
    }

    /**
     * @hidden
     * @param iceP_rtspUrl -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_playAsync(String iceP_rtspUrl, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "play", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_rtspUrl);
                 }, null);
        return f;
    }

    default void pause(String rtspUrl)
    {
        pause(rtspUrl, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void pause(String rtspUrl, java.util.Map<String, String> context)
    {
        _iceI_pauseAsync(rtspUrl, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> pauseAsync(String rtspUrl)
    {
        return _iceI_pauseAsync(rtspUrl, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> pauseAsync(String rtspUrl, java.util.Map<String, String> context)
    {
        return _iceI_pauseAsync(rtspUrl, context, false);
    }

    /**
     * @hidden
     * @param iceP_rtspUrl -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_pauseAsync(String iceP_rtspUrl, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "pause", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_rtspUrl);
                 }, null);
        return f;
    }

    default void close(String rtspUrl)
    {
        close(rtspUrl, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void close(String rtspUrl, java.util.Map<String, String> context)
    {
        _iceI_closeAsync(rtspUrl, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> closeAsync(String rtspUrl)
    {
        return _iceI_closeAsync(rtspUrl, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> closeAsync(String rtspUrl, java.util.Map<String, String> context)
    {
        return _iceI_closeAsync(rtspUrl, context, false);
    }

    /**
     * @hidden
     * @param iceP_rtspUrl -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_closeAsync(String iceP_rtspUrl, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "close", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_rtspUrl);
                 }, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static AudioPlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), AudioPlayerPrx.class, _AudioPlayerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static AudioPlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), AudioPlayerPrx.class, _AudioPlayerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static AudioPlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), AudioPlayerPrx.class, _AudioPlayerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static AudioPlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), AudioPlayerPrx.class, _AudioPlayerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static AudioPlayerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, AudioPlayerPrx.class, _AudioPlayerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static AudioPlayerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, AudioPlayerPrx.class, _AudioPlayerPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default AudioPlayerPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (AudioPlayerPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default AudioPlayerPrx ice_adapterId(String newAdapterId)
    {
        return (AudioPlayerPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default AudioPlayerPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (AudioPlayerPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default AudioPlayerPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (AudioPlayerPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default AudioPlayerPrx ice_invocationTimeout(int newTimeout)
    {
        return (AudioPlayerPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default AudioPlayerPrx ice_connectionCached(boolean newCache)
    {
        return (AudioPlayerPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default AudioPlayerPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (AudioPlayerPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default AudioPlayerPrx ice_secure(boolean b)
    {
        return (AudioPlayerPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default AudioPlayerPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (AudioPlayerPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default AudioPlayerPrx ice_preferSecure(boolean b)
    {
        return (AudioPlayerPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default AudioPlayerPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (AudioPlayerPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default AudioPlayerPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (AudioPlayerPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default AudioPlayerPrx ice_collocationOptimized(boolean b)
    {
        return (AudioPlayerPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default AudioPlayerPrx ice_twoway()
    {
        return (AudioPlayerPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default AudioPlayerPrx ice_oneway()
    {
        return (AudioPlayerPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default AudioPlayerPrx ice_batchOneway()
    {
        return (AudioPlayerPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default AudioPlayerPrx ice_datagram()
    {
        return (AudioPlayerPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default AudioPlayerPrx ice_batchDatagram()
    {
        return (AudioPlayerPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default AudioPlayerPrx ice_compress(boolean co)
    {
        return (AudioPlayerPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default AudioPlayerPrx ice_timeout(int t)
    {
        return (AudioPlayerPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default AudioPlayerPrx ice_connectionId(String connectionId)
    {
        return (AudioPlayerPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default AudioPlayerPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (AudioPlayerPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::Soup::AudioPlayer";
    }
}